package com.grinaldi.moovi.data.sources.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShows(
    @SerializedName("results")
    val tvShows: List<TvShow>
)
