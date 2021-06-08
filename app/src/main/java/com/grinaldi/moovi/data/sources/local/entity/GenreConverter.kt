package com.grinaldi.moovi.data.sources.local.entity

import androidx.room.TypeConverter

@TypeConverter
fun genreListFromString(value: String?): List<String>? {
    return value?.let {
        val genres = it.split(',')
        return genres.map { genre -> genre }
    }
}

@TypeConverter
fun stringFromGenres(genres: List<String>?): String? {
    return genres?.joinToString { genre -> genre }
}