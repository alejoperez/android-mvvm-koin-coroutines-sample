package com.mvvm.koin.coroutines.sample.data.user

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.preference.PreferenceManager
import com.mvvm.koin.coroutines.sample.data.room.User
import com.mvvm.koin.coroutines.sample.webservice.LoginRequest
import com.mvvm.koin.coroutines.sample.webservice.LoginResponse
import com.mvvm.koin.coroutines.sample.webservice.RegisterRequest
import com.mvvm.koin.coroutines.sample.webservice.RegisterResponse
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.ArgumentMatchers

@Suppress("DeferredResultUnused")
@ExperimentalCoroutinesApi
class UserRepositoryTest : BaseTest() {

    private val user: User = mock()
    private val requestLogin: LoginRequest = mock()
    private val responseLogin: LoginResponse = mock()
    private val requestRegister: RegisterRequest = mock()
    private val responseRegister: RegisterResponse = mock()

    private val localDataSource = declareMock<UserLocalDataSource>()
    private val remoteDataSource = declareMock<UserRemoteDataSource>()
    private val preferenceManager = declareMock<PreferenceManager>()

    private val userRepository by inject<UserRepository>()

    @Before
    fun setUpMocks() {
        doNothing().whenever(preferenceManager).putPreference(ArgumentMatchers.anyString(), eq(Any()))
    }

    @Test
    fun saveUserAsyncTest() {
        runBlocking {
            userRepository.saveUserAsync(user)

            verifyZeroInteractions(remoteDataSource)
            verify(localDataSource, times(1)).saveUserAsync(user)
        }
    }

    @Test
    fun getUserAsyncTest() {
        runBlocking {
            userRepository.getUserAsync()

            verifyZeroInteractions(remoteDataSource)

            verify(localDataSource, times(1)).getUserAsync()

        }
    }

    @Test
    fun loginAsyncTest() {
        runBlocking {
            val result = responseLogin.toDeferred()
            whenever(remoteDataSource.loginAsync(requestLogin)).thenReturn(result)

            userRepository.loginAsync(requestLogin)

            verify(remoteDataSource, times(1)).loginAsync(requestLogin)
            verify(localDataSource, times(1)).saveUserAsync(result.await().toUser())

        }
    }

    @Test
    fun registerAsyncTest() {
        runBlocking {
            val result = responseRegister.toDeferred()
            whenever(remoteDataSource.registerAsync(requestRegister)).thenReturn(result)

            userRepository.registerAsync(requestRegister)

            verify(remoteDataSource, times(1)).registerAsync(requestRegister)

            verify(localDataSource, times(1)).saveUserAsync(result.await().toUser())
        }
    }

    @Test
    fun isLoggedInTest() {
        userRepository.isLoggedIn()

        verifyZeroInteractions(remoteDataSource)

        verify(localDataSource, times(1)).isLoggedIn()
    }

    @Test
    fun logoutTest() {
        userRepository.logout()

        verifyZeroInteractions(remoteDataSource)

        verify(localDataSource, times(1)).logout()

    }

}