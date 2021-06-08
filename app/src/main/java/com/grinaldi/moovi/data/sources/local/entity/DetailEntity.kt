package com.grinaldi.moovi.data.sources.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE

@Entity(tableName = "details")
data class DetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val id: Int,

    val overview: String?,
    val title: String?,
    val posterPath: String?,
    val voteAverage: Double?,
    val popularity: Double?,
    val tagLine: String?,
    val genres: List<String> = listOf(),
    val homepage: String?,
    val status: String?,
    val movieType: Int = TYPE_MOVIE
)