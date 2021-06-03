package com.grinaldi.moovi.tv

import com.grinaldi.moovi.views.tv.TvViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvViewModelTest {
    private var mViewModel: TvViewModel? = null

    @Before
    fun setUp() {
        mViewModel = TvViewModel()
    }

    @Test
    fun testGetTvShowViewModel() {
        Assert.assertNotNull(mViewModel?.getTvShow())
        Assert.assertEquals(15, mViewModel?.getTvShow()?.size)
    }
}