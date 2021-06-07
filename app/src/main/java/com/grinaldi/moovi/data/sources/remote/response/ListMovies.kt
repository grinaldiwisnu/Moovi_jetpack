package com.grinaldi.moovi.data.sources.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovies(
    @SerializedName("results")
    val movies: List<Movies>
)
