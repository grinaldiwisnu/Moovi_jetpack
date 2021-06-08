package com.grinaldi.moovi.data.sources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val overview: String?,
    val title: String?,
    val posterPath: String?,
    val voteAverage: Double?,
    val movieType: Int = TYPE_MOVIE,
    val isFavorite: Boolean? = false
)