package com.grinaldi.moovi.ui.tv

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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {
    private lateinit var tvViewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun init() {
        tvViewModel = TvViewModel(repository)
    }

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Test
    fun getAllTvShows() {
        testCoroutineRule.runBlockingTest {
            val dummyTvShows = DummyData.getDummyListData()
            `when`(repository.getTvShowList()).thenReturn(dummyTvShows)
            val movieEntities = tvViewModel.getAllTvShows()
            assertNotNull(movieEntities)
            verify(repository).getTvShowList()
            tvViewModel.getAllTvShows().observeForever(observer)
            verify(observer).onChanged(dummyTvShows)
        }
    }
}