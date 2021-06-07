package com.grinaldi.moovi.data.sources.remote.response

import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int,
)
