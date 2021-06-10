package com.grinaldi.moovi.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.utils.DummyData
import com.grinaldi.moovi.utils.Resource
import com.grinaldi.moovi.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody.Companion.toResponseBody
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
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<DetailEntity>>

    @Mock
    private lateinit var isFavoriteObserver: Observer<Boolean>

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var responseHttpError: HttpException

    private var dummyDetail = DummyData.getDummyDetailData()
    private var dummyEntity = DummyData.getDummyListData()[0]

    @Before
    fun init() {
        detailViewModel = DetailViewModel(repository)
        val responseBody = Response.error<Error>(500, "".toByteArray().toResponseBody(null))
        responseHttpError = HttpException(responseBody)
    }

    @Test
    fun `get movie detail success`() {
        testCoroutineRule.runBlockingTest {
            val movieId = 1
            val resource = Resource.success(dummyDetail)
            val expectation = MutableLiveData<Resource<DetailEntity>>()
            expectation.value = resource
            `when`(repository.getMovieDetail(movieId)).thenReturn(expectation)

            val result = detailViewModel.getMovieDetail(movieId)
            result.observeForever(observer)
            verify(repository).getMovieDetail(movieId)

            assertNotNull(result)
            observer.onChanged(resource)
        }
    }

    @Test
    fun `get tv show detail success`() {
        testCoroutineRule.runBlockingTest {
            val movieId = 1
            val resource = Resource.success(dummyDetail)
            val expectation = MutableLiveData<Resource<DetailEntity>>()
            expectation.value = resource
            `when`(repository.getTvShowDetail(movieId)).thenReturn(expectation)

            val result = detailViewModel.getTvShowDetail(movieId)
            assertNotNull(result)

            verify(repository).getTvShowDetail(movieId)
            detailViewModel.getTvShowDetail(movieId).observeForever(observer)
            observer.onChanged(resource)
        }
    }

    @Test
    fun `check if the movie is favorite`() {
        val movieId = 1
        val expectation = MutableLiveData<Boolean>()
        expectation.value = false
        `when`(repository.checkMovieFavorite(movieId)).thenReturn(expectation)

        val result = detailViewModel.checkIsMovieFavorite(movieId)
        result.observeForever(isFavoriteObserver)
        verify(repository).checkMovieFavorite(movieId)
        verify(isFavoriteObserver).onChanged(expectation.value)

        assertNotNull(result.value)
        assertEquals(expectation.value, result.value)
    }

    @Test
    fun `add a movie to favorite`() {
        testCoroutineRule.runBlockingTest {
            val id = 1
            `when`(repository.insertFavoriteMovie(id)).thenReturn(Unit)
            detailViewModel.addMovieToFavorite(dummyEntity)
            verify(repository).insertFavoriteMovie(id)
        }
    }

    @Test
    fun `remove a movie from favorite`() {
        testCoroutineRule.runBlockingTest {
            val id = 1
            `when`(repository.deleteFavoriteMovie(id)).thenReturn(Unit)
            detailViewModel.deleteMovieFromFavorite(dummyEntity)
            verify(repository).deleteFavoriteMovie(id)
        }
    }
}