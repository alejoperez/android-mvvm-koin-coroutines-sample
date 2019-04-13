package com.mvvm.koin.coroutines.sample.data.places

import com.mvvm.koin.coroutines.sample.data.room.Place
import com.mvvm.koin.coroutines.sample.webservice.IApi
import kotlinx.coroutines.Deferred
import java.lang.UnsupportedOperationException

class PlacesRemoteDataSource(private val api: IApi)  : IPlacesDataSource {

    override suspend fun savePlacesAsync(places: List<Place>) = throw UnsupportedOperationException()

    override suspend fun getPlacesAsync(): Deferred<List<Place>> = api.getPlacesAsync()

}