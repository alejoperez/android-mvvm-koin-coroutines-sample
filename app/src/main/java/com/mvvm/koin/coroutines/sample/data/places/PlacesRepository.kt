package com.mvvm.koin.coroutines.sample.data.places

import androidx.annotation.VisibleForTesting
import com.mvvm.koin.coroutines.sample.data.room.Place
import kotlinx.coroutines.Deferred

class PlacesRepository(private val localDataSource: PlacesLocalDataSource,
                       private val remoteDataSource: PlacesRemoteDataSource) : IPlacesDataSource {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var hasCache = false

    override suspend fun getPlacesAsync(): Deferred<List<Place>> {
        return if (hasCache) {
            localDataSource.getPlacesAsync()
        } else {
            remoteDataSource.getPlacesAsync().also { savePlacesAsync(it.await()) }
        }
    }

    override suspend fun savePlacesAsync(places: List<Place>) {
        localDataSource.savePlacesAsync(places).also { hasCache = true }
    }

    fun invalidateCache() {
        hasCache = false
    }
}