package com.grinaldi.moovi.data

import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.data.sources.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestMovieRepository(private val remoteDataSource: RemoteDataSource) : DataSource {
    override suspend fun getMovieList(): List<MovieEntity> {
        val result = withContext(Dispatchers.IO) { remoteDataSource.getMovieList() }
        val movieList = ArrayList<MovieEntity>()
        result.movies.forEach { movie ->
            movieList.add(
                MovieEntity(
                    overview = movie.overview,
                    title = movie.title,
                    posterPath = movie.posterPath,
                    voteAverage = movie.voteAverage,
                    id = movie.id,
                )
            )
        }
        return movieList
    }

    override suspend fun getTvShowList(): List<MovieEntity> {
        val result = withContext(Dispatchers.IO) { remoteDataSource.getTvShowList() }
        val tvShowList = ArrayList<MovieEntity>()
        result.tvShows.forEach { tvShow ->
            tvShowList.add(
                MovieEntity(
                    overview = tvShow.overview,
                    title = tvShow.name,
                    posterPath = tvShow.posterPath,
                    voteAverage = tvShow.voteAverage,
                    id = tvShow.id,
                )
            )
        }
        return tvShowList
    }

    override suspend fun getMovieDetail(movieId: Int): DetailEntity {
        val movieDetail = remoteDataSource.getMovieDetail(movieId)
        return DetailEntity(
            id = movieDetail.id,
            title = movieDetail.title,
            overview = movieDetail.overview,
            posterPath = movieDetail.posterPath,
            backdropPath = movieDetail.backdropPath,
            releaseDate = movieDetail.releaseDate,
            voteCount = movieDetail.voteCount,
            voteAverage = movieDetail.voteAverage,
            tagLine = movieDetail.tagLine,
            homepage = movieDetail.homepage,
            status = movieDetail.status
        )
    }

    override suspend fun getTvShowDetail(tvShowId: Int): DetailEntity {
        val tvShowDetail = remoteDataSource.getTvShowDetail(tvShowId)
        return DetailEntity(
            id = tvShowDetail.id,
            title = tvShowDetail.title,
            overview = tvShowDetail.overview,
            posterPath = tvShowDetail.posterPath,
            backdropPath = tvShowDetail.backdropPath,
            releaseDate = tvShowDetail.releaseDate,
            voteCount = tvShowDetail.voteCount,
            voteAverage = tvShowDetail.voteAverage,
            tagLine = tvShowDetail.tagLine,
            homepage = tvShowDetail.homepage,
            status = tvShowDetail.status
        )
    }
}