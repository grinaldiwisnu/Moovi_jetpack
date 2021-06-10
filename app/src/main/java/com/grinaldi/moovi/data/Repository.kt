package com.grinaldi.moovi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.grinaldi.moovi.data.sources.local.LocalDataSource
import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.data.sources.remote.RemoteDataSource
import com.grinaldi.moovi.data.sources.remote.network.NetworkBound
import com.grinaldi.moovi.data.sources.remote.response.*
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE
import com.grinaldi.moovi.utils.Constants.TYPE_TV_SHOW
import com.grinaldi.moovi.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DataSource {
    override fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBound<PagedList<MovieEntity>, ListMovies>() {
            override fun populateDataFromDb() = localDataSource.getMovies().toLiveData(4)

            override fun shouldFetch(data: PagedList<MovieEntity>?) = data.isNullOrEmpty()

            override fun networkCall(): LiveData<ApiResponse<ListMovies>> {
                val result = MutableLiveData<ApiResponse<ListMovies>>()
                CoroutineScope(Dispatchers.Main).launch {
                    remoteDataSource.getMovieList().observeForever {
                        result.postValue(it)
                    }
                }
                return result
            }

            override fun saveCallResult(data: ListMovies) {
                CoroutineScope(Dispatchers.Main).launch {
                    data.movies.forEach { movie ->
                        val entity = MovieEntity(
                            overview = movie.overview,
                            title = movie.title,
                            posterPath = movie.posterPath,
                            voteAverage = movie.voteAverage,
                            movieType = TYPE_MOVIE,
                            id = movie.id
                        )
                        launch { localDataSource.insertMovie(entity) }
                    }
                }
            }
        }.asLiveData()
    }

    override fun getTvShowsList(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBound<PagedList<MovieEntity>, ListTvShows>() {
            override fun populateDataFromDb() = localDataSource.getTvShows().toLiveData(4)

            override fun shouldFetch(data: PagedList<MovieEntity>?) = data.isNullOrEmpty()

            override fun networkCall(): LiveData<ApiResponse<ListTvShows>> {
                val result = MutableLiveData<ApiResponse<ListTvShows>>()
                CoroutineScope(Dispatchers.Main).launch {
                    remoteDataSource.getTvShowList().observeForever {
                        result.postValue(it)
                    }
                }
                return result
            }

            override fun saveCallResult(data: ListTvShows) {
                CoroutineScope(Dispatchers.Main).launch {
                    data.tvShows.forEach { movie ->
                        val entity = MovieEntity(
                            overview = movie.overview,
                            title = movie.name,
                            posterPath = movie.posterPath,
                            voteAverage = movie.voteAverage,
                            movieType = TYPE_TV_SHOW,
                            id = movie.id
                        )
                        launch { localDataSource.insertMovie(entity) }
                    }
                }
            }
        }.asLiveData()
    }

    override fun getMovieDetail(id: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBound<DetailEntity, MovieDetail>() {
            override fun populateDataFromDb() = localDataSource.getMovieDetail(id)

            override fun shouldFetch(data: DetailEntity?) = data == null

            override fun networkCall(): LiveData<ApiResponse<MovieDetail>> {
                val result = MutableLiveData<ApiResponse<MovieDetail>>()
                CoroutineScope(Dispatchers.Main).launch {
                    remoteDataSource.getMovieDetail(id).observeForever {
                        result.postValue(it)
                    }
                }
                return result
            }

            override fun saveCallResult(data: MovieDetail) {
                CoroutineScope(Dispatchers.Main).launch {
                    val genres = data.genres.map { genre -> genre.name }
                    val detail = DetailEntity(
                        data.id,
                        data.overview,
                        data.title,
                        data.posterPath,
                        data.voteAverage,
                        data.popularity,
                        data.tagLine,
                        genres,
                        data.homepage,
                        data.status,
                        TYPE_MOVIE
                    )
                    localDataSource.insertDetailMovie(detail)
                }
            }
        }.asLiveData()
    }

    override fun getTvShowDetail(id: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBound<DetailEntity, TvShowDetail>() {
            override fun populateDataFromDb() = localDataSource.getTvShowDetail(id)

            override fun shouldFetch(data: DetailEntity?) = data == null

            override fun networkCall(): LiveData<ApiResponse<TvShowDetail>> {
                val result = MutableLiveData<ApiResponse<TvShowDetail>>()
                CoroutineScope(Dispatchers.Main).launch {
                    remoteDataSource.getTvShowDetail(id).observeForever {
                        result.postValue(it)
                    }
                }
                return result
            }

            override fun saveCallResult(data: TvShowDetail) {
                CoroutineScope(Dispatchers.Main).launch {
                    val genres = data.genres.map { genre -> genre.name }
                    val detail = DetailEntity(
                        data.id,
                        data.overview,
                        data.title,
                        data.posterPath,
                        data.voteAverage,
                        data.popularity,
                        data.tagLine,
                        genres,
                        data.homepage,
                        data.status,
                        TYPE_TV_SHOW
                    )
                    localDataSource.insertDetailMovie(detail)
                }
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        return localDataSource.getFavoriteMovies().toLiveData(4)
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>> {
        return localDataSource.getFavoriteTvShows().toLiveData(4)
    }

    override fun checkMovieFavorite(id: Int): LiveData<Boolean> {
        return localDataSource.checkMovieFavorite(id)
    }

    override suspend fun insertFavoriteMovie(id: Int) {
        localDataSource.addFavoriteMovie(id)
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        localDataSource.deleteFavoriteMovie(id)
    }
}