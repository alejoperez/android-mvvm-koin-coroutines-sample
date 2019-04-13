package com.mvvm.koin.coroutines.sample.data.places

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.room.Place
import com.mvvm.koin.coroutines.sample.webservice.IApi
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock
import java.lang.UnsupportedOperationException

@Suppress("DeferredResultUnused")
@ExperimentalCoroutinesApi
class PlacesRemoteDataSourceTest : BaseTest() {

    private val places: List<Place> = mock()
    private val api: IApi = declareMock()
    private val placesRemoteDataSource by inject<PlacesRemoteDataSource>()

    @Test(expected = UnsupportedOperationException::class)
    fun savePlacesAsyncTest() {
        runBlocking {
            try {
                placesRemoteDataSource.savePlacesAsync(places)
            } finally {
                verifyZeroInteractions(api)
            }
        }
    }

    @Test
    fun getPlacesAsync() {
        runBlocking {
            placesRemoteDataSource.getPlacesAsync()
            verify(api, times(1)).getPlacesAsync()
        }
    }

}