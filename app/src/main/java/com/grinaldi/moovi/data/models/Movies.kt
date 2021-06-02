package com.grinaldi.moovi.data.models

data class Movies(
    var id: Int? = null,
    var vote_count: Int? = null,
    var isVideo: Boolean? = null,
    var vote_average: Double? = null,
    var title: String? = null,
    var popularity: Double? = null,
    var poster_path: String? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var genre_ids: List<Int>?,
    var backdrop_path: String? = null,
    var adult: Boolean? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var first_air_date: String? = null,
    var favorite: String?
)
