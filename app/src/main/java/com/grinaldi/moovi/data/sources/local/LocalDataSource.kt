package com.grinaldi.moovi.data.sources.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.grinaldi.moovi.data.sources.local.database.MovieDao
import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    suspend fun insertMovie(movie: MovieEntity) {
        withContext(Dispatchers.IO) { movieDao.insertMovie(movie) }
    }

    suspend fun insertDetailMovie(movieDetail: DetailEntity) {
        withContext(Dispatchers.IO) { movieDao.insertDetail(movieDetail) }
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getMovies()
    }

    fun getMovieDetail(movieId: Int): LiveData<DetailEntity> {
        return movieDao.getMovieDetail(movieId)
    }

    fun getTvShows(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getTvShows()
    }

    fun getTvShowDetail(tvShowId: Int): LiveData<DetailEntity> {
        return movieDao.getTvDetail(tvShowId)
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getFavoriteMovies()
    }

    fun getFavoriteTvShows(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getFavoriteTvShows()
    }

    fun checkMovieFavorite(id: Int): LiveData<Boolean> {
        return movieDao.checkMovieFavorite(id)
    }

    suspend fun addFavoriteMovie(id: Int) {
        withContext(Dispatchers.IO) { movieDao.addFavorite(id) }
    }

    suspend fun deleteFavoriteMovie(id: Int) {
        withContext(Dispatchers.IO) { movieDao.removeFavorite(id) }
    }
}