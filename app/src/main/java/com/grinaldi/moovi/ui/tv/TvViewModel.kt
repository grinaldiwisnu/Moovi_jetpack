package com.grinaldi.moovi.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import kotlinx.coroutines.launch

class TvViewModel(private val repository: Repository) : ViewModel() {
    private val tvShowList = MutableLiveData<List<MovieEntity>>()

    fun getAllTvShows(): LiveData<List<MovieEntity>> {
        viewModelScope.launch {
            val result = repository.getTvShowList()
            tvShowList.postValue(result)
        }
        return tvShowList
    }
}