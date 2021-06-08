package com.grinaldi.moovi.data.sources.local.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE
import com.grinaldi.moovi.utils.Constants.TYPE_TV_SHOW

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies WHERE movieType = $TYPE_MOVIE")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE movieType = $TYPE_TV_SHOW")
    fun getTvShows(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM details WHERE movie_id = :movie_id AND movieType = $TYPE_MOVIE")
    fun getMovieDetail(movie_id: Int): LiveData<DetailEntity>

    @Query("SELECT * FROM details WHERE movie_id = :movie_id AND movieType = $TYPE_TV_SHOW")
    fun getTvDetail(movie_id: Int): LiveData<DetailEntity>

    @Query("SELECT * FROM movies WHERE movieType = $TYPE_MOVIE AND isFavorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE movieType = $TYPE_TV_SHOW AND isFavorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, MovieEntity>

    @Insert
    fun insertMovie(movieEntity: MovieEntity)

    @Insert
    fun insertDetail(detailEntity: DetailEntity)

    @Query("UPDATE movies SET isFavorite = 1 WHERE id = :id")
    fun addFavorite(id: Int)

    @Query("UPDATE movies SET isFavorite = 0 WHERE id = :id")
    fun removeFavorite(id: Int)

    @Query("SELECT isFavorite == 1 FROM movies WHERE id = :id")
    fun checkMovieFavorite(id: Int): LiveData<Boolean>
}