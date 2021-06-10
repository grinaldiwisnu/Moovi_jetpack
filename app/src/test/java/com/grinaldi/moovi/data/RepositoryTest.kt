package com.grinaldi.moovi.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.grinaldi.moovi.data.sources.local.LocalDataSource
import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.data.sources.remote.RemoteDataSource
import com.grinaldi.moovi.utils.*
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
@ExperimentalCoroutinesApi
class RepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var remoteDataSource = mock(RemoteDataSource::class.java)
    private var localDataSource = mock(LocalDataSource::class.java)

    private lateinit var repository: Repository

    @Before
    fun init() {
        repository = Repository(remoteDataSource, localDataSource)
    }

    @Test
    fun `get movie list success`() {
        testCoroutineRule.runBlockingTest {
            val data = DummyData.getDummyListData()
            val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
            `when`(localDataSource.getMovies()).thenReturn(dataSourceFactory)
            repository.getMovieList()

            val result = Resource.success(PagedListUtil.mockPagedList(data))
            verify(localDataSource).getMovies()
            assertNotNull(result.data)
        }
    }

    @Test
    fun `get tv show list success`() {
        testCoroutineRule.runBlockingTest {
            val data = DummyData.getDummyListData()
            val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
            `when`(localDataSource.getTvShows()).thenReturn(dataSourceFactory)
            repository.getTvShowsList()

            val result = Resource.success(PagedListUtil.mockPagedList(data))
            verify(localDataSource).getTvShows()
            assertNotNull(result.data)
        }
    }

    @Test
    fun `get movie detail success`() {
        testCoroutineRule.runBlockingTest {
            val id = 1
            val response = DummyData.getDummyDetailData()
            val expectation = MutableLiveData<DetailEntity>()
            expectation.value = response
            `when`(localDataSource.getMovieDetail(id)).thenReturn(expectation)

            val result = repository.getMovieDetail(id)
            assertNotNull(result)

            verify(localDataSource).getMovieDetail(id)
        }
    }

    @Test
    fun `get tv show detail success`() {
        testCoroutineRule.runBlockingTest {
            val id = 1
            val response = DummyData.getDummyDetailData()
            val expectation = MutableLiveData<DetailEntity>()
            expectation.value = response
            `when`(localDataSource.getTvShowDetail(id)).thenReturn(expectation)

            val result = repository.getTvShowDetail(id)
            assertNotNull(result)

            verify(localDataSource).getTvShowDetail(id)
        }
    }

    @Test
    fun `get favorite movies list`() {
        val response = DummyData.getDummyListData()
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getFavoriteMovies()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovies()

        val result = PagedListUtil.mockPagedList(response)
        verify(localDataSource).getFavoriteMovies()

        assertNotNull(result)
        assertEquals(response.size, result.size)
    }

    @Test
    fun `get favorite tv shows list`() {
        val response = DummyData.getDummyListData()
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getFavoriteTvShows()).thenReturn(dataSourceFactory)

        repository.getFavoriteTvShows()
        val result = PagedListUtil.mockPagedList(response)
        verify(localDataSource).getFavoriteTvShows()

        assertNotNull(result)
        assertEquals(response.size, result.size)
    }

    @Test
    fun `add a movie to favorite`() {
        testCoroutineRule.runBlockingTest {
            val id = 1
            `when`(localDataSource.addFavoriteMovie(id)).thenReturn(Unit)
            repository.insertFavoriteMovie(id)

            verify(localDataSource).addFavoriteMovie(id)
        }
    }

    @Test
    fun `remove a movie from favorite`() {
        testCoroutineRule.runBlockingTest {
            val id = 1
            `when`(localDataSource.deleteFavoriteMovie(id)).thenReturn(Unit)
            repository.deleteFavoriteMovie(id)

            verify(localDataSource).deleteFavoriteMovie(id)
        }
    }

    @Test
    fun checkMovieFavorite() {
        val expected = false
        val movieId = 1
        val isFavorite = MutableLiveData<Boolean>()
        isFavorite.value = false
        `when`(localDataSource.checkMovieFavorite(movieId)).thenReturn(isFavorite)

        val result = LiveDataTestUtil.getValue(repository.checkMovieFavorite(movieId))
        verify(localDataSource).checkMovieFavorite(movieId)

        assertEquals(expected, result)
    }
}