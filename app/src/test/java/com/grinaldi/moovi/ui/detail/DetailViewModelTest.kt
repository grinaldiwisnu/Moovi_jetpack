package com.grinaldi.moovi.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
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
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    private val dummyDetail = DummyData.getDummyDetailData()

    companion object {
        const val TYPE_MOVIE = 1
        const val TYPE_TV_SHOW = 2
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<DetailEntity>

    @Before
    fun init() {
        detailViewModel = DetailViewModel(repository)
    }

    @Test
    fun getMovieDetail() {
        testCoroutineRule.runBlockingTest {
            val movieId = 1

            val movieDetail = dummyDetail

            `when`(repository.getMovieDetail(movieId)).thenReturn(movieDetail)
            val movieDetailResult = detailViewModel.getMovieDetail(TYPE_MOVIE, movieId)
            verify(repository).getMovieDetail(movieId)
            assertNotNull(movieDetailResult)

            detailViewModel.getMovieDetail(TYPE_MOVIE, movieId).observeForever(observer)
            verify(observer).onChanged(dummyDetail)
        }
    }

    @Test
    fun getTvShowDetail() {
        testCoroutineRule.runBlockingTest {
            val tvShowId = 1

            val tvShowDetail = dummyDetail

            `when`(repository.getTvShowDetail(tvShowId)).thenReturn(tvShowDetail)
            val tvShowDetailResult = detailViewModel.getMovieDetail(TYPE_TV_SHOW, tvShowId)
            verify(repository).getTvShowDetail(tvShowId)
            assertNotNull(tvShowDetailResult)

            detailViewModel.getMovieDetail(TYPE_TV_SHOW, tvShowId).observeForever(observer)
            verify(observer).onChanged(dummyDetail)
        }
    }
}