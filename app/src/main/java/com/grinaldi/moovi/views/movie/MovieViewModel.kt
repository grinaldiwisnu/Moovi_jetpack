package com.grinaldi.moovi.views.movie

import androidx.lifecycle.ViewModel
import com.grinaldi.moovi.data.models.Movie
import com.grinaldi.moovi.data.sources.MockMovieSource

class MovieViewModel : ViewModel() {
    fun getMovies(): List<Movie> = MockMovieSource.getAllMovies()
    fun detailMovie(id: Int): Movie? = MockMovieSource.getMovieById(id)
}