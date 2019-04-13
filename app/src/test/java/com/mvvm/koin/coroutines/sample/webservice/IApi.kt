package com.mvvm.koin.coroutines.sample.webservice

import com.mvvm.koin.coroutines.sample.base.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject

@ExperimentalCoroutinesApi
class IApiTest: BaseTest() {

    private val api by inject<IApi>()

    @Test
    fun loginAsyncTest() {
        runBlocking {
            val response = api.loginAsync(LoginRequest("","")).await()
            Assert.assertNotNull("Response should not be null",response)
        }
    }

    @Test
    fun registerAsyncTest() {
        runBlocking {
            val response = api.registerAsync(RegisterRequest("","","")).await()
            Assert.assertNotNull("Response should not be null",response)
        }
    }

    @Test
    fun getPlacesAsyncTest() {
        runBlocking {
            val response = api.getPlacesAsync().await()
            Assert.assertNotNull("Response should not be null",response)
            Assert.assertTrue("places should not be empty",response.isNotEmpty())
        }
    }

    @Test
    fun getPhotosAsyncTest() {
        runBlocking {
            val response = api.getPhotosAsync().await()
            Assert.assertNotNull("Response should not be null",response)
            Assert.assertTrue("photos should not be empty",response.isNotEmpty())
        }
    }
}