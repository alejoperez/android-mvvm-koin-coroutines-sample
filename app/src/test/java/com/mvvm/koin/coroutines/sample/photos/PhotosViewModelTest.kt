package com.mvvm.koin.coroutines.sample.photos

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.photos.PhotosRepository
import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.mvvm.koin.coroutines.sample.livedata.Status
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock
import retrofit2.HttpException

@Suppress("DeferredResultUnused")
@ExperimentalCoroutinesApi
class PhotosViewModelTest : BaseTest() {

    companion object {
        const val MSG_PROGRESS_TRUE = "Progress should be true"
        const val MSG_PROGRESS_FALSE = "Progress should be false"

        const val MSG_EVENT_NULL_VALUE = "Even shouldn't be null"
        const val MSG_INVALID_STATUS_SUCCESS = "Status should de SUCCESS"
        const val MSG_INVALID_STATUS_FAILURE = "Status should de FAILURE"
        const val MSG_INVALID_STATUS_NETWORK_ERROR = "Status should de NETWORK_ERROR"
    }

    private val photosRepository: PhotosRepository = declareMock()
    private val viewModel by inject<PhotosViewModel>()

    @Test
    fun showProgressTest() {
        viewModel.showProgress()
        Assert.assertTrue(MSG_PROGRESS_TRUE, viewModel.isLoading.get())
    }

    @Test
    fun hideProgressTest() {
        viewModel.hideProgress()
        Assert.assertFalse(MSG_PROGRESS_FALSE, viewModel.isLoading.get())
    }


    @Test
    fun photosSuccessTest() {
        runBlocking {
            val response = mock<List<Photo>>()
            whenever(photosRepository.getPhotosAsync()).thenReturn(response.toDeferred())
        }
        viewModel.getPhotos()

        val event = viewModel.photos.value
        assertNotNull(MSG_EVENT_NULL_VALUE, event)
        assertEquals(MSG_INVALID_STATUS_SUCCESS, Status.SUCCESS, event?.status)
    }

    @Test
    fun photosFailureTest() {
        runBlocking {
            doThrow(HttpException(mock())).whenever(photosRepository).getPhotosAsync()
        }
        viewModel.getPhotos()

        val event = viewModel.photos.value
        assertNotNull(MSG_EVENT_NULL_VALUE, event)
        assertEquals(MSG_INVALID_STATUS_FAILURE, Status.FAILURE, event?.status)
    }


    @Test
    fun photosNetworkErrorTest() {
        runBlocking {
            doThrow(RuntimeException()).whenever(photosRepository).getPhotosAsync()
        }
        viewModel.getPhotos()

        val event = viewModel.photos.value
        assertNotNull(MSG_EVENT_NULL_VALUE, event)
        assertEquals(MSG_INVALID_STATUS_NETWORK_ERROR, Status.NETWORK_ERROR, event?.status)
    }

}