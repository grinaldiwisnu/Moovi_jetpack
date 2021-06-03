package com.grinaldi.moovi.movie

import com.grinaldi.moovi.views.movie.MovieViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private var mViewModel: MovieViewModel? = null

    @Before
    fun setUp() {
        mViewModel = MovieViewModel()
    }

    @Test
    fun testGetMoviesViewModel() {
        assertNotNull(mViewModel?.getMovies())
        assertEquals(15, mViewModel?.getMovies()?.size)
    }
}