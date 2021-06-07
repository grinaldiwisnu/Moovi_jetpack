package com.grinaldi.moovi.data.sources.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("tagline")
    val tagLine: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("status")
    val status: String? = null
)