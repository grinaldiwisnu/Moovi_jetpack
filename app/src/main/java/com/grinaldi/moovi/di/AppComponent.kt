package com.grinaldi.moovi.di

import com.grinaldi.moovi.ui.detail.DetailActivity
import com.grinaldi.moovi.ui.favoritemovie.FavoriteMovieFragment
import com.grinaldi.moovi.ui.movie.MovieFragment
import com.grinaldi.moovi.ui.tv.TvFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, StorageModule::class])
interface AppComponent {
    fun inject(movieFragment: MovieFragment)
    fun inject(tvFragment: TvFragment)
    fun inject(detailActivity: DetailActivity)
    fun inject(favoriteMovieFragment: FavoriteMovieFragment)
}