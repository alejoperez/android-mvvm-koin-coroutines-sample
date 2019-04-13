package com.mvvm.koin.coroutines.sample.main

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.room.User
import com.mvvm.koin.coroutines.sample.data.user.UserRepository
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
class MainViewModelTest : BaseTest() {

    companion object {
        const val MSG_EVENT_NULL_VALUE = "Even shouldn't be null"
        const val MSG_INVALID_STATUS_SUCCESS = "Status should de SUCCESS"
        const val MSG_INVALID_STATUS_FAILURE = "Status should de FAILURE"
        const val MSG_INVALID_STATUS_NETWORK_ERROR = "Status should de NETWORK_ERROR"
    }

    private val userRepository: UserRepository = declareMock()
    private val viewModel by inject<MainViewModel>()

    @Test
    fun getUserSuccessTest() {
        runBlocking {
            val response = mock<User>()
            whenever(userRepository.getUserAsync()).thenReturn(response.toDeferred())
        }
        viewModel.getUser()

        val event = viewModel.user.value
        Assert.assertNotNull(MSG_EVENT_NULL_VALUE, event)
        Assert.assertEquals(MSG_INVALID_STATUS_SUCCESS, Status.SUCCESS, event?.status)
    }

    @Test
    fun getUserErrorTest() {
        runBlocking {
            doThrow(HttpException(mock())).whenever(userRepository).getUserAsync()
        }
        viewModel.getUser()

        val event = viewModel.user.value

        assertNotNull(MSG_EVENT_NULL_VALUE, event)
        assertEquals(MSG_INVALID_STATUS_FAILURE, Status.FAILURE, event?.status)
    }

    @Test
    fun getUserNetworkErrorTest() {
        runBlocking {
            doThrow(RuntimeException()).whenever(userRepository).getUserAsync()

            viewModel.getUser()

            val event = viewModel.user.value

            assertNotNull(MSG_EVENT_NULL_VALUE, event)
            assertEquals(MSG_INVALID_STATUS_NETWORK_ERROR, Status.NETWORK_ERROR, event?.status)
        }
    }

}