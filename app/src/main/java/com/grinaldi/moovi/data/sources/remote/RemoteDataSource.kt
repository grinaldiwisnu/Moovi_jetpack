package com.grinaldi.moovi.data.sources.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grinaldi.moovi.data.sources.remote.network.MovieInterface
import com.grinaldi.moovi.data.sources.remote.response.*
import com.grinaldi.moovi.utils.ErrorHandler
import com.grinaldi.moovi.utils.IdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val movieInterface: MovieInterface) {
    suspend fun getMovieList(): LiveData<ApiResponse<ListMovies>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<ListMovies>>()
        try {
            val data = withContext(Dispatchers.IO) { movieInterface.getNowPlayingMovies() }
            if (data.movies.isEmpty()) {
                result.postValue(ApiResponse.empty("No Data Found", data))
            } else {
                result.postValue(ApiResponse.success(data))
            }
        } catch (error: Exception) {
            result.postValue(ApiResponse.error(ErrorHandler.generateErrorMessage(error)))
        }
        IdlingResource.decrement()
        return result
    }

    suspend fun getTvShowList(): LiveData<ApiResponse<ListTvShows>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<ListTvShows>>()
        try {
            val data = withContext(Dispatchers.IO) { movieInterface.getPopularTvShows() }
            if (data.tvShows.isEmpty()) {
                result.postValue(ApiResponse.empty("No Data Found", data))
            } else {
                result.postValue(ApiResponse.success(data))
            }
        } catch (error: Exception) {
            result.postValue(ApiResponse.error(ErrorHandler.generateErrorMessage(error)))
        }
        IdlingResource.decrement()
        return result
    }

    suspend fun getMovieDetail(id: Int): LiveData<ApiResponse<MovieDetail>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieDetail>>()
        try {
            val data = withContext(Dispatchers.IO) { movieInterface.getMovieDetail(id) }
            result.postValue(ApiResponse.success(data))
        } catch (error: Exception) {
            result.postValue(ApiResponse.error(ErrorHandler.generateErrorMessage(error)))
        }
        IdlingResource.decrement()
        return result
    }

    suspend fun getTvShowDetail(id: Int): LiveData<ApiResponse<TvShowDetail>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvShowDetail>>()
        try {
            val data = withContext(Dispatchers.IO) { movieInterface.getTvShowDetail(id) }
            result.postValue(ApiResponse.success(data))
        } catch (error: Exception) {
            result.postValue(ApiResponse.error(ErrorHandler.generateErrorMessage(error)))
        }
        IdlingResource.decrement()
        return result
    }
}