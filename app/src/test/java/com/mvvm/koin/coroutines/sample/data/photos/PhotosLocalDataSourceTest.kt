package com.mvvm.koin.coroutines.sample.data.photos

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.mvvm.koin.coroutines.sample.data.room.PhotoDao
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.*
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock

@ExperimentalCoroutinesApi
class PhotosLocalDataSourceTest : BaseTest() {

    private val photos: List<Photo> = mock()
    private val photosDao: PhotoDao = declareMock()
    private val photosLocalDataSource by inject<PhotosLocalDataSource>()

    @Test
    fun savePhotosAsyncTest() {
        runBlocking {
            photosLocalDataSource.savePhotosAsync(photos)
            verify(photosDao, times(1)).savePhotos(photos)
        }
    }

    @Test
    fun getPhotosAsync() {
        runBlocking {
            photosLocalDataSource.getPhotosAsync().await()
            verify(photosDao, times(1)).getPhotos()
        }
    }

}