package com.grinaldi.moovi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getMovieDetail(id: Int) = repository.getMovieDetail(id)

    fun getTvShowDetail(id: Int) = repository.getTvShowDetail(id)

    fun checkIsMovieFavorite(id: Int): LiveData<Boolean> {
        return repository.checkMovieFavorite(id)
    }

    fun addMovieToFavorite(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.insertFavoriteMovie(movieEntity.id)
        }
    }

    fun deleteMovieFromFavorite(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.deleteFavoriteMovie(movieEntity.id)
        }
    }
}