package com.grinaldi.moovi.ui.movie

import androidx.lifecycle.ViewModel
import com.grinaldi.moovi.data.Repository
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getAllMovies() = repository.getMovieList()
}