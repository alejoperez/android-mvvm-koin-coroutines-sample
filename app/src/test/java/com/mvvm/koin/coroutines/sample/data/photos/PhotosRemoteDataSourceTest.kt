package com.mvvm.koin.coroutines.sample.data.photos

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.mvvm.koin.coroutines.sample.webservice.IApi
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock
import java.lang.UnsupportedOperationException

@Suppress("DeferredResultUnused")
@ExperimentalCoroutinesApi
class PhotosRemoteDataSourceTest : BaseTest() {

    private val photos:List<Photo> = mock()
    private val api: IApi = declareMock()
    private val photosRemoteDataSource by inject<PhotosRemoteDataSource>()

    @Test(expected = UnsupportedOperationException::class)
    fun savePhotosAsyncTest() {
        runBlocking {
            photosRemoteDataSource.savePhotosAsync(photos)
        }
    }

    @Test
    fun getPhotosAsync() {
        runBlocking {
            photosRemoteDataSource.getPhotosAsync()
            verify(api, times(1)).getPhotosAsync()
        }
    }
}