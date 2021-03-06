package com.grinaldi.moovi.data.sources.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("title", alternate = ["name"])
    val title: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("tagline")
    val tagLine: String? = "-",
    @SerializedName("genres")
    val genres: List<Genre> = listOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("homepage")
    val homepage: String? = "-",
    @SerializedName("status")
    val status: String? = "-",
    @SerializedName("popularity")
    val popularity: Double? = null
)
