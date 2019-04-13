package com.mvvm.koin.coroutines.sample.data.places

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.room.Place
import com.mvvm.koin.coroutines.sample.data.room.PlaceDao
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock

@ExperimentalCoroutinesApi
class PlacesLocalDataSourceTest: BaseTest() {

    private val places: List<Place> = mock()
    private val placesDao: PlaceDao = declareMock()
    private val placesLocalDataSource by inject<PlacesLocalDataSource>()

    @Test
    fun savePlacesAsyncTest() {
        runBlocking {
            placesLocalDataSource.savePlacesAsync(places)
            verify(placesDao, times(1)).savePlaces(places)
        }
    }

    @Test
    fun getPlacesAsync() {
        runBlocking {
            placesLocalDataSource.getPlacesAsync().await()
            verify(placesDao, times(1)).getPlaces()
        }
    }

}