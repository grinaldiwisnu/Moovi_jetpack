package com.grinaldi.moovi.ui.favoritemovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import javax.inject.Inject

class FavoriteMovieViewModel @Inject constructor(private val repository: Repository) {
    private val errorMessage = MutableLiveData<String>()

    fun getErrorMessage(): LiveData<String> = errorMessage

    fun getFavoriteMovieList(): LiveData<PagedList<MovieEntity>> {
        val result = repository.getFavoriteMovies()
        result.observeForever {
            val message = if (it.isEmpty()) "No Data" else ""
            errorMessage.postValue(message)
        }
        return result
    }

    fun getFavoriteTvShowList(): LiveData<PagedList<MovieEntity>> {
        val result = repository.getFavoriteTvShows()
        result.observeForever {
            val message = if (it.isEmpty()) "No Data" else ""
            errorMessage.postValue(message)
        }
        return result
    }
}