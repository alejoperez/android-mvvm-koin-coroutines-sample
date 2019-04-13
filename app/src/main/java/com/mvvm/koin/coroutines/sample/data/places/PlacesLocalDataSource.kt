package com.mvvm.koin.coroutines.sample.data.places

import com.mvvm.koin.coroutines.sample.coroutines.ICoroutineContextProvider
import com.mvvm.koin.coroutines.sample.data.room.Place
import com.mvvm.koin.coroutines.sample.data.room.PlaceDao
import kotlinx.coroutines.*

class PlacesLocalDataSource(private val placesDao: PlaceDao, private val provider: ICoroutineContextProvider) : IPlacesDataSource {

    override suspend fun savePlacesAsync(places: List<Place>) = withContext(provider.getIoContext()) { placesDao.savePlaces(places) }

    override suspend fun getPlacesAsync(): Deferred<List<Place>> = CoroutineScope(provider.getIoContext()).async { placesDao.getPlaces() }

}