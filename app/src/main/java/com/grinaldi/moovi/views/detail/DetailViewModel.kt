package com.grinaldi.moovi.views.detail

import androidx.lifecycle.ViewModel
import com.grinaldi.moovi.data.models.Movie
import com.grinaldi.moovi.data.sources.MockMovieSource

class DetailViewModel : ViewModel() {
    fun detailMovie(id: Int): Movie? = MockMovieSource.getMovieById(id)
    fun detailTvShow(id: Int): Movie? = MockMovieSource.getTvById(id)
}