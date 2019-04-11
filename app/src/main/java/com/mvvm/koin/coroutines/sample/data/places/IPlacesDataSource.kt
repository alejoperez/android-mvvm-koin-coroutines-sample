package com.mvvm.koin.coroutines.sample.data.places

import com.mvvm.koin.coroutines.sample.data.room.Place
import kotlinx.coroutines.Deferred

interface IPlacesDataSource {

    suspend fun getPlacesAsync(): Deferred<List<Place>>

    suspend fun savePlacesAsync(places: List<Place>)
}