package com.mvvm.koin.coroutines.sample.data.places

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.room.Place
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock

@Suppress("DeferredResultUnused")
@ExperimentalCoroutinesApi
class PlacesRepositoryTest: BaseTest() {

    private val places: List<Place> = mock()

    private val localDataSource = declareMock<PlacesLocalDataSource>()
    private val remoteDataSource = declareMock<PlacesRemoteDataSource>()

    private val placesRepository by inject<PlacesRepository>()

    @Test
    fun getPlacesAsyncCacheTest() {
        runBlocking {
            placesRepository.hasCache = true
            placesRepository.getPlacesAsync()

            verifyZeroInteractions(remoteDataSource)
            verify(localDataSource, times(1)).getPlacesAsync()
        }
    }

    @Test
    fun getPlacesAsyncRemoteTest() {
        runBlocking {

            whenever(remoteDataSource.getPlacesAsync()).thenReturn(places.toDeferred())

            placesRepository.hasCache = false
            placesRepository.getPlacesAsync()

            verify(remoteDataSource, times(1)).getPlacesAsync()
            verify(localDataSource, times(1)).savePlacesAsync(places)
        }
    }

    @Test
    fun savePlacesAsyncTest() {
        runBlocking {
            placesRepository.hasCache = true
            placesRepository.savePlacesAsync(places)

            verifyZeroInteractions(remoteDataSource)
            verify(localDataSource, times(1)).savePlacesAsync(places)
        }
    }
}