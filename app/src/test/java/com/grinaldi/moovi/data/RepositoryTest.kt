package com.grinaldi.moovi.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grinaldi.moovi.data.sources.remote.RemoteDataSource
import com.grinaldi.moovi.utils.DummyData
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = TestMovieRepository(remote)

    private val movieResponse = DummyData.getDummyMovieResponse()
    private val movieDetailResponse = DummyData.getDummyMovieDetailResponse()
    private val tvShowResponse = DummyData.getDummyTvShowResponse()
    private val tvShowDetailResponse = DummyData.getDummyTvShowDetailResponse()

    @Test
    fun getAllMovies() = runBlocking {
        `when`(remote.getMovieList()).thenReturn(movieResponse)
        val movieListTest = movieRepository.getMovieList()
        verify(remote).getMovieList()
        assertNotNull(movieListTest)
        assertEquals(movieResponse.movies.size, movieListTest.size)
    }

    @Test
    fun getMovieDetail() = runBlocking {
        val movieId = 1
        `when`(remote.getMovieDetail(eq(movieId))).thenReturn(movieDetailResponse)
        val movieDetailTest = movieRepository.getMovieDetail(movieId)
        verify(remote).getMovieDetail(eq(movieId))
        assertNotNull(movieDetailTest)
        assertEquals(movieDetailResponse.title, movieDetailTest.title)
    }

    @Test
    fun getAllTvShows() = runBlocking {
        `when`(remote.getTvShowList()).thenReturn(tvShowResponse)
        val tvShowListTest = movieRepository.getTvShowList()
        verify(remote).getTvShowList()
        assertNotNull(tvShowListTest)
        assertEquals(tvShowResponse.tvShows.size, tvShowListTest.size)
    }

    @Test
    fun getTvShowDetail() = runBlocking {
        val tvShowId = 1
        `when`(remote.getTvShowDetail(eq(tvShowId))).thenReturn(tvShowDetailResponse)
        val tvShowDetailTest = movieRepository.getTvShowDetail(tvShowId)
        verify(remote).getTvShowDetail(eq(tvShowId))
        assertNotNull(tvShowDetailTest)
        assertEquals(tvShowDetailResponse.title, tvShowDetailTest.title)
    }
}