package com.mvvm.koin.coroutines.sample.data.photos

import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.mvvm.koin.coroutines.sample.webservice.IApi
import kotlinx.coroutines.Deferred
import java.lang.UnsupportedOperationException

class PhotosRemoteDataSource(private val api: IApi) : IPhotosDataSource {

    override suspend fun savePhotosAsync(photos: List<Photo>) = throw UnsupportedOperationException()

    override suspend fun getPhotosAsync(): Deferred<List<Photo>> = api.getPhotosAsync()

}