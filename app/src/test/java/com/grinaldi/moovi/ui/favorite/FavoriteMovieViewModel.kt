package com.grinaldi.moovi.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.ui.favoritemovie.FavoriteMovieViewModel
import org.junit.Assert.assertEquals
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
class FavoriteMovieViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var listObserver: Observer<List<MovieEntity>>

    @Mock
    private lateinit var errorObserver: Observer<String>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    private lateinit var favoriteViewModel: FavoriteMovieViewModel

    @Before
    fun init() {
        favoriteViewModel = FavoriteMovieViewModel(repository)
    }

    @Test
    fun `get favorite movies success`() {
        val dummyList = pagedList
        `when`(dummyList.size).thenReturn(10)
        val expectation = MutableLiveData<PagedList<MovieEntity>>()
        expectation.value = dummyList
        `when`(repository.getFavoriteMovies()).thenReturn(expectation)
        val favoriteMovies = favoriteViewModel.getFavoriteMovieList().value
        verify(repository).getFavoriteMovies()
        assertNotNull(favoriteMovies)
        assertEquals(dummyList.size, favoriteMovies?.size)
        favoriteViewModel.getFavoriteMovieList().observeForever(listObserver)
        favoriteViewModel.getErrorMessage().observeForever(errorObserver)
        verify(listObserver).onChanged(dummyList)
    }

    @Test
    fun `get favorite tv shows success`() {
        val dummyList = pagedList
        `when`(dummyList.size).thenReturn(10)
        val expectation = MutableLiveData<PagedList<MovieEntity>>()
        expectation.value = dummyList
        `when`(repository.getFavoriteTvShows()).thenReturn(expectation)
        val favoriteMovies = favoriteViewModel.getFavoriteTvShowList().value
        verify(repository).getFavoriteTvShows()
        assertNotNull(favoriteMovies)
        assertEquals(dummyList.size, favoriteMovies?.size)
        favoriteViewModel.getFavoriteTvShowList().observeForever(listObserver)
        favoriteViewModel.getErrorMessage().observeForever(errorObserver)
        verify(listObserver).onChanged(dummyList)
    }
}
