package com.grinaldi.moovi.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.di.Injection
import com.grinaldi.moovi.ui.detail.DetailViewModel
import com.grinaldi.moovi.ui.movie.MovieViewModel
import com.grinaldi.moovi.ui.tv.TvViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}