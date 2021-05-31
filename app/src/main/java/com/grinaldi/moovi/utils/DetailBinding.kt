package com.grinaldi.moovi.utils

import android.widget.ImageView
import com.grinaldi.moovi.data.models.Movie

interface DetailBinding {

    fun setBinding(movie: Movie)
    fun multipleGlide(firstImage: ImageView, secondImage: ImageView, movie: Movie)
}