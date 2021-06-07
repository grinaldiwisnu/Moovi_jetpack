package com.grinaldi.moovi.data

import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity

interface DataSource {
    suspend fun getMovieList(): List<MovieEntity>

    suspend fun getTvShowList(): List<MovieEntity>

    suspend fun getMovieDetail(movieId: Int): DetailEntity

    suspend fun getTvShowDetail(tvShowId: Int): DetailEntity
}