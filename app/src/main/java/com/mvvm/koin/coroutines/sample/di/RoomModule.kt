package com.mvvm.koin.coroutines.sample.di

import android.content.Context
import com.mvvm.koin.coroutines.sample.data.room.PhotoDao
import com.mvvm.koin.coroutines.sample.data.room.PlaceDao
import com.mvvm.koin.coroutines.sample.data.room.SampleDataBase
import com.mvvm.koin.coroutines.sample.data.room.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(context: Context): SampleDataBase = SampleDataBase.buildDatabase(context)

    @Singleton
    @Provides
    fun provideUserDao(database: SampleDataBase): UserDao = database.userDao()

    @Singleton
    @Provides
    fun providePlaceDao(database: SampleDataBase): PlaceDao = database.placeDao()

    @Singleton
    @Provides
    fun providePhotoDao(database: SampleDataBase): PhotoDao = database.photoDao()

}