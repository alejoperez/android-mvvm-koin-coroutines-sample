package com.mvvm.koin.coroutines.sample.data.photos

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock

@Suppress("DeferredResultUnused")
@ExperimentalCoroutinesApi
class PhotosRepositoryTest: BaseTest() {

    private val photos: List<Photo> = mock()

    private val localDataSource: PhotosLocalDataSource = declareMock()
    private val remoteDataSource: PhotosRemoteDataSource = declareMock()

    private val photosRepository by inject<PhotosRepository>()

    @Test
    fun getPhotosAsyncCacheTest() {
        runBlocking {
            photosRepository.hasCache = true
            photosRepository.getPhotosAsync()

            verifyZeroInteractions(remoteDataSource)
            verify(localDataSource, times(1)).getPhotosAsync()
        }
    }

    @Test
    fun getPhotosAsyncRemoteTest() {
        runBlocking {

            whenever(remoteDataSource.getPhotosAsync()).thenReturn(photos.toDeferred())

            photosRepository.hasCache = false
            photosRepository.getPhotosAsync()

            verify(remoteDataSource, times(1)).getPhotosAsync()
            verify(localDataSource, times(1)).savePhotosAsync(photos)

        }
    }

    @Test
    fun savePhotosAsyncTest() {
        runBlocking {
            photosRepository.hasCache = true
            photosRepository.savePhotosAsync(photos)

            verifyZeroInteractions(remoteDataSource)
            verify(localDataSource, times(1)).savePhotosAsync(photos)

        }
    }

}