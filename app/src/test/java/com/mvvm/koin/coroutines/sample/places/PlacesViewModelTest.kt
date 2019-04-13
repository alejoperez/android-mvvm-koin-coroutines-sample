package com.mvvm.koin.coroutines.sample.places

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.places.PlacesRepository
import com.mvvm.koin.coroutines.sample.data.room.Place
import com.mvvm.koin.coroutines.sample.livedata.Status
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock
import retrofit2.HttpException

@Suppress("DeferredResultUnused")
@ExperimentalCoroutinesApi
class PlacesViewModelTest : BaseTest() {

    companion object {
        const val MSG_EVENT_NULL_VALUE = "Even shouldn't be null"
        const val MSG_INVALID_STATUS_SUCCESS = "Status should de SUCCESS"
        const val MSG_INVALID_STATUS_FAILURE = "Status should de FAILURE"
        const val MSG_INVALID_STATUS_NETWORK_ERROR = "Status should de NETWORK_ERROR"
    }

    private val placesRepository: PlacesRepository = declareMock()
    private val viewModel by inject<PlacesViewModel>()

    @Test
    fun getPlacesSuccessTest() {
        runBlocking {
            val response = mock<List<Place>>()
            whenever(placesRepository.getPlacesAsync()).thenReturn(response.toDeferred())
        }
        viewModel.getPlaces()

        val event = viewModel.places.value
        Assert.assertNotNull(MSG_EVENT_NULL_VALUE, event)
        Assert.assertEquals(MSG_INVALID_STATUS_SUCCESS, Status.SUCCESS, event?.status)

    }

    @Test
    fun getPlacesErrorTest() {
        runBlocking {
            doThrow(HttpException(mock())).whenever(placesRepository).getPlacesAsync()
        }

        viewModel.getPlaces()

        val event = viewModel.places.value

        Assert.assertNotNull(MSG_EVENT_NULL_VALUE, event)
        Assert.assertEquals(MSG_INVALID_STATUS_FAILURE, Status.FAILURE, event?.status)
    }

    @Test
    fun getPlacesNetworkErrorTest() {
        runBlocking {
            doThrow(RuntimeException()).whenever(placesRepository).getPlacesAsync()
        }
        viewModel.getPlaces()

        val event = viewModel.places.value

        Assert.assertNotNull(MSG_EVENT_NULL_VALUE, event)
        Assert.assertEquals(MSG_INVALID_STATUS_NETWORK_ERROR, Status.NETWORK_ERROR, event?.status)

    }

}