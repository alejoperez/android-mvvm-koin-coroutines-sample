package com.mvvm.koin.coroutines.sample.data.user

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.room.User
import com.mvvm.koin.coroutines.sample.webservice.IApi
import com.mvvm.koin.coroutines.sample.webservice.LoginRequest
import com.mvvm.koin.coroutines.sample.webservice.RegisterRequest
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock

@Suppress("DeferredResultUnused")
@ExperimentalCoroutinesApi
class UserRemoteDataSourceTest : BaseTest() {

    private val user: User = mock()
    private val loginRequest: LoginRequest = mock()
    private val registerRequest: RegisterRequest = mock()

    private val api: IApi = declareMock()

    private val userRemoteDataSource by inject<UserRemoteDataSource>()

    @Test
    fun loginAsyncTest() {
        runBlocking {
            userRemoteDataSource.loginAsync(loginRequest)
            verify(api, times(1)).loginAsync(loginRequest)
        }
    }

    @Test
    fun registerAsyncTest() {
        runBlocking {
            userRemoteDataSource.registerAsync(registerRequest)
            verify(api, times(1)).registerAsync(registerRequest)
        }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getUserAsyncTest() {
        runBlocking {
            userRemoteDataSource.getUserAsync()
        }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveUserAsyncTest() {
        runBlocking {
            try {
                userRemoteDataSource.saveUserAsync(user)
            } finally {
                verifyZeroInteractions(api)
            }
        }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun isLoggedInTest() {
        userRemoteDataSource.isLoggedIn()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun logoutTest() {
        userRemoteDataSource.isLoggedIn()
    }

}