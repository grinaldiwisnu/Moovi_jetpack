package com.grinaldi.moovi.data.sources.remote.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.grinaldi.moovi.data.sources.remote.response.ApiResponse
import com.grinaldi.moovi.utils.Resource
import com.grinaldi.moovi.utils.StatusResponse

abstract class NetworkBound<ResultType, RequestType> {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.postValue(Resource.loading(null))

        @Suppress("LeakingThis")
        val dataFromDb = populateDataFromDb()
        result.addSource(dataFromDb) {
            if (shouldFetch(it)) {
                fetchDataFromNetwork(dataFromDb)
            } else {
                result.postValue(Resource.success(it))
            }
        }
    }

    fun asLiveData() = result

    protected abstract fun populateDataFromDb(): LiveData<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract fun networkCall(): LiveData<ApiResponse<RequestType>>
    protected abstract fun saveCallResult(data: RequestType)

    @Suppress("UNCHECKED_CAST")
    private fun fetchDataFromNetwork(dataFromDb: LiveData<ResultType>) {
        val apiResponse = networkCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dataFromDb)
            when (response.status) {
                StatusResponse.SUCCESS -> {
                    saveCallResult(response.body as RequestType)
                    result.addSource(populateDataFromDb()) { dbData ->
                        if (dbData != null) {
                            result.postValue(Resource.success(dbData))
                        }
                    }
                }

                StatusResponse.EMPTY -> {
                    result.addSource(populateDataFromDb()) { dbData ->
                        result.postValue(Resource.success(dbData))
                    }
                }
                StatusResponse.ERROR -> {
                    result.addSource(dataFromDb) { newData ->
                        result.postValue(Resource.error(response.message, newData))
                    }
                }
                else -> result.addSource(dataFromDb) { newData ->
                    result.postValue(Resource.error(response.message, newData))
                }
            }
        }
    }
}