package com.grinaldi.moovi.data

import com.grinaldi.moovi.data.sources.MockMovieSource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieSourceTest {
    private var dataSource: MockMovieSource? = null

    @Before
    fun setUp() {
        dataSource = MockMovieSource
    }

    @Test
    fun movies() {
        assertNotNull(dataSource?.getAllMovies())
        assertEquals(15, dataSource?.getAllMovies()?.size)
    }

    @Test
    fun tvshow() {
        assertNotNull(dataSource?.getAllTvShows())
        assertEquals(15, dataSource?.getAllTvShows()?.size)
    }
}