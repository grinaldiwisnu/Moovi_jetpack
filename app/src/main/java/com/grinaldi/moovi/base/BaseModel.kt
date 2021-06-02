package com.grinaldi.moovi.base

data class BaseModel<T>(
    var page: Int,
    var total_results: Int,
    var total_pages: Int,
    val message: String? = null,
    var results: T? = null
)