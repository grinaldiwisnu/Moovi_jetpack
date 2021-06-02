package com.grinaldi.moovi.data.sources

import com.grinaldi.moovi.data.models.Movie
import com.grinaldi.moovi.data.sources.remote.RemoteDataSource


class DataRepository(val remoteDataSource: RemoteDataSource) : DataSource {

    // remote repo
    override fun getAllData(type: String, filter: String, callback: DataSource.GetAllDataCallback) {
        remoteDataSource.getAllData(type, filter, object : DataSource.GetAllDataCallback {
            override fun onSuccess(data: List<Movie>) {
                callback.onSuccess(data)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }

            override fun onFinish() {
                callback.onFinish()
            }
        })
    }

    override fun onClearDisposables() {
        remoteDataSource.onClearDisposables()
    }

    companion object {

        private var mRepository: DataRepository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: RemoteDataSource): DataRepository {
            if (mRepository == null) {
                mRepository = DataRepository(remoteDataSource = remoteDataSource)
            }
            return mRepository!!
        }
    }
}