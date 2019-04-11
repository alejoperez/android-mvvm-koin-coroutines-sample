package com.mvvm.koin.coroutines.sample.data.photos

import com.mvvm.koin.coroutines.sample.data.room.Photo
import kotlinx.coroutines.Deferred

interface IPhotosDataSource {

    suspend fun getPhotosAsync(): Deferred<List<Photo>>

    suspend fun savePhotosAsync(photos: List<Photo>)

}