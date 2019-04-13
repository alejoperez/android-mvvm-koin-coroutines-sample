package com.mvvm.koin.coroutines.sample.data.photos

import com.mvvm.koin.coroutines.sample.coroutines.ICoroutineContextProvider
import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.mvvm.koin.coroutines.sample.data.room.PhotoDao
import kotlinx.coroutines.*

class PhotosLocalDataSource(private val photoDao: PhotoDao, private val provider: ICoroutineContextProvider) : IPhotosDataSource {

    override suspend fun savePhotosAsync(photos: List<Photo>) = withContext(provider.getIoContext()) { photoDao.savePhotos(photos) }

    override suspend fun getPhotosAsync(): Deferred<List<Photo>> = CoroutineScope(provider.getIoContext()).async { photoDao.getPhotos() }

}