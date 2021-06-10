package com.grinaldi.moovi.ui.tv

import androidx.lifecycle.ViewModel
import com.grinaldi.moovi.data.Repository
import javax.inject.Inject

class TvViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getAllTvShows() = repository.getTvShowsList()
}