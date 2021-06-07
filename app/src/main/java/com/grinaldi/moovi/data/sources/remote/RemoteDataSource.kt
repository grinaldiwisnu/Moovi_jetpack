package com.grinaldi.moovi.data.sources.remote

import com.grinaldi.moovi.data.sources.remote.network.NetworkService
import com.grinaldi.moovi.data.sources.remote.response.ListMovies
import com.grinaldi.moovi.data.sources.remote.response.ListTvShows
import com.grinaldi.moovi.data.sources.remote.response.MovieDetail
import com.grinaldi.moovi.data.sources.remote.response.TvShowDetail
import com.grinaldi.moovi.utils.IdlingResource

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource {
            return instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
        }
    }

    suspend fun getMovieList(): ListMovies {
        IdlingResource.increment()
        val result = NetworkService.getInstance().getNowPlayingMovies()
        IdlingResource.decrement()
        return result
    }

    suspend fun getTvShowList(): ListTvShows {
        IdlingResource.increment()
        val result = NetworkService.getInstance().getPopularTvShows()
        IdlingResource.decrement()
        return result
    }

    suspend fun getMovieDetail(movieId: Int): MovieDetail {
        IdlingResource.increment()
        val result = NetworkService.getInstance().getMovieDetail(movieId)
        IdlingResource.decrement()
        return result
    }

    suspend fun getTvShowDetail(tvShowId: Int): TvShowDetail {
        IdlingResource.increment()
        val result = NetworkService.getInstance().getTvShowDetail(tvShowId)
        IdlingResource.decrement()
        return result
    }
}