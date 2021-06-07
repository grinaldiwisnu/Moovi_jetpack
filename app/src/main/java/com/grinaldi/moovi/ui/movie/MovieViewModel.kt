package com.grinaldi.moovi.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository) : ViewModel() {
    private val movieList = MutableLiveData<List<MovieEntity>>()

    fun getAllMovies(): LiveData<List<MovieEntity>> {
        viewModelScope.launch {
            val movies = repository.getMovieList()
            movieList.postValue(movies)
        }
        return movieList
    }
}