package com.grinaldi.moovi.views.tv

import androidx.lifecycle.ViewModel
import com.grinaldi.moovi.data.models.Movie
import com.grinaldi.moovi.data.sources.MockMovieSource

class TvViewModel : ViewModel() {
    fun getTvShow(): List<Movie> = MockMovieSource.getAllTvShows()
}