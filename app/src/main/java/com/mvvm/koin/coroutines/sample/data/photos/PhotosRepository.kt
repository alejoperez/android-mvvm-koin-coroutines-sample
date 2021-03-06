package com.mvvm.koin.coroutines.sample.data.photos

import androidx.annotation.VisibleForTesting
import com.mvvm.koin.coroutines.sample.data.room.Photo
import kotlinx.coroutines.Deferred

class PhotosRepository(private val localDataSource: PhotosLocalDataSource,
                       private val remoteDataSource: PhotosRemoteDataSource) : IPhotosDataSource {


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var hasCache = false

    override suspend fun getPhotosAsync(): Deferred<List<Photo>> {
        return if (hasCache) {
            localDataSource.getPhotosAsync()
        } else {
            remoteDataSource.getPhotosAsync().also { savePhotosAsync(it.await()) }
        }
    }

    override suspend fun savePhotosAsync(photos: List<Photo>) {
        localDataSource.savePhotosAsync(photos).also { hasCache = true }
    }

    fun invalidateCache() {
        hasCache = false
    }
}