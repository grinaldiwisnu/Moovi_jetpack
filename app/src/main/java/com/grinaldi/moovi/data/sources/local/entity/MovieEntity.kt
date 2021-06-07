package com.grinaldi.moovi.data.sources.local.entity

import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE

data class MovieEntity(
    val id: Int?,
    val overview: String?,
    val title: String?,
    val posterPath: String?,
    val voteAverage: Double?,
    val movieType: Int? = TYPE_MOVIE
)