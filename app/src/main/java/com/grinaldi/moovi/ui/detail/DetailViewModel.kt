package com.grinaldi.moovi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    var movie = MutableLiveData<MovieEntity>()

    fun getMovieDetail(type: Int, contentId: Int): LiveData<DetailEntity> {
        val result = MutableLiveData<DetailEntity>()
        viewModelScope.launch {
            val favoriteUsers = when (type) {
                TYPE_MOVIE -> repository.getMovieDetail(contentId)
                else -> repository.getTvShowDetail(contentId)
            }
            result.postValue(favoriteUsers)
        }
        return result
    }
}