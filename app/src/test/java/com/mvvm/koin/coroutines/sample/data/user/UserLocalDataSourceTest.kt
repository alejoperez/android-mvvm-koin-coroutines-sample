package com.mvvm.koin.coroutines.sample.data.user

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.preference.PreferenceManager
import com.mvvm.koin.coroutines.sample.data.room.User
import com.mvvm.koin.coroutines.sample.data.room.UserDao
import com.mvvm.koin.coroutines.sample.webservice.LoginRequest
import com.mvvm.koin.coroutines.sample.webservice.RegisterRequest
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
class UserLocalDataSourceTest : BaseTest() {

    private val user: User = mock()
    private val loginRequest: LoginRequest = mock()
    private val registerRequest: RegisterRequest = mock()

    private val userDao: UserDao = declareMock()
    private val preferenceManager: PreferenceManager = declareMock()

    private val userLocalDataSource by inject<UserLocalDataSource>()

    @Before
    fun setUpMocks() {
        whenever(preferenceManager.findPreference(PreferenceManager.ACCESS_TOKEN, "")).thenReturn("")
        doNothing().whenever(preferenceManager).putPreference(ArgumentMatchers.anyString(), eq(Any()))
    }

    @Test
    fun getUserAsyncTest() {
        runBlocking {
            userLocalDataSource.getUserAsync().await()
            verify(userDao, times(1)).getUser()
        }
    }

    @Test
    fun saveUserAsyncTest() {
        runBlocking {
            userLocalDataSource.saveUserAsync(user)
            verify(userDao, times(1)).saveUser(user)
        }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun loginAsyncTest() {
        runBlocking {
            userLocalDataSource.loginAsync(loginRequest)
        }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun registerAsyncTest() {
        runBlocking {
            userLocalDataSource.registerAsync(registerRequest)
        }
    }

    @Test
    fun isLoggedInTest() {
        userLocalDataSource.isLoggedIn()
        verify(preferenceManager, times(1)).findPreference(PreferenceManager.ACCESS_TOKEN, "")
    }

    @Test
    fun logoutTest() {
        userLocalDataSource.logout()
        verify(preferenceManager, times(1)).putPreference(PreferenceManager.ACCESS_TOKEN, "")
    }

}