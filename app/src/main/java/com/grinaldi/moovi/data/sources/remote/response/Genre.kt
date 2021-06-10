package com.grinaldi.moovi.data.sources.remote.response

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String = ""
)