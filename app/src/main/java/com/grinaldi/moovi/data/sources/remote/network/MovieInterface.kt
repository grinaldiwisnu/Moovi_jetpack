package com.grinaldi.moovi.data.sources.remote.network

import com.grinaldi.moovi.BuildConfig
import com.grinaldi.moovi.data.sources.remote.response.ListMovies
import com.grinaldi.moovi.data.sources.remote.response.ListTvShows
import com.grinaldi.moovi.data.sources.remote.response.MovieDetail
import com.grinaldi.moovi.data.sources.remote.response.TvShowDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String = BuildConfig.TMDB_API): ListMovies

    @GET("tv/on_the_air")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String = BuildConfig.TMDB_API): ListTvShows

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API
    ): MovieDetail

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API
    ): TvShowDetail
}