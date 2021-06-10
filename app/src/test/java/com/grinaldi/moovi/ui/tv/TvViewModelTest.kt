package com.grinaldi.moovi.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.utils.Resource
import com.grinaldi.moovi.utils.Status
import com.grinaldi.moovi.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var listObserver: Observer<Resource<PagedList<MovieEntity>>>

    private lateinit var tvViewModel: TvViewModel
    private lateinit var responseHttpError: HttpException

    @Before
    fun init() {
        tvViewModel = TvViewModel(repository)
        val responseBody = Response.error<Error>(500, "".toByteArray().toResponseBody(null))
        responseHttpError = HttpException(responseBody)
    }

    @Test
    fun `get TV Show list success`() {
        testCoroutineRule.runBlockingTest {
            val dummyList = pagedList
            val value = Resource(Status.SUCCESS, dummyList, null)
            val expectation = MutableLiveData<Resource<PagedList<MovieEntity>>>()
            expectation.value = value
            `when`(repository.getTvShowsList()).thenReturn(expectation)

            val result = tvViewModel.getAllTvShows()
            result.observeForever(listObserver)
            verify(listObserver).onChanged(value)
            verify(repository).getTvShowsList()

            assertEquals(expectation.value, result.value)
            assertEquals(expectation.value?.data, result.value?.data)
            assertNull(result.value?.message)
        }
    }

    @Test
    fun `get TV Show list with no data`() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "No Data Found"
            val dummyList = pagedList
            val value = Resource(Status.ERROR, dummyList, errorMessage)
            val expectation = MutableLiveData<Resource<PagedList<MovieEntity>>>()
            expectation.value = value
            `when`(repository.getTvShowsList()).thenReturn(expectation)

            val result = tvViewModel.getAllTvShows()
            result.observeForever(listObserver)
            verify(listObserver).onChanged(value)
            verify(repository).getTvShowsList()

            assertEquals(expectation.value, result.value)
            assertEquals(expectation.value?.message, result.value?.message)
            assertEquals(expectation.value?.message, errorMessage)
        }
    }
}