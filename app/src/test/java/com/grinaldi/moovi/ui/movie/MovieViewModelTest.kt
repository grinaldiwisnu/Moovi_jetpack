package com.grinaldi.moovi.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.utils.DummyData
import com.grinaldi.moovi.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun init() {
        movieViewModel = MovieViewModel(repository)
    }

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Test
    fun getAllMovies() {
        testCoroutineRule.runBlockingTest {
            val dummyMovies = DummyData.getDummyListData()
            `when`(repository.getMovieList()).thenReturn(dummyMovies)
            val movieEntities = movieViewModel.getAllMovies()
            assertNotNull(movieEntities)
            verify(repository).getMovieList()
            movieViewModel.getAllMovies().observeForever(observer)
            verify(observer).onChanged(dummyMovies)
        }
    }
}