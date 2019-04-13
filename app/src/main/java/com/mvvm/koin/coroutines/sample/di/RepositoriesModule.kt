package com.mvvm.koin.coroutines.sample.di

import com.mvvm.koin.coroutines.sample.data.photos.PhotosLocalDataSource
import com.mvvm.koin.coroutines.sample.data.photos.PhotosRemoteDataSource
import com.mvvm.koin.coroutines.sample.data.photos.PhotosRepository
import com.mvvm.koin.coroutines.sample.data.places.PlacesLocalDataSource
import com.mvvm.koin.coroutines.sample.data.places.PlacesRemoteDataSource
import com.mvvm.koin.coroutines.sample.data.places.PlacesRepository
import com.mvvm.koin.coroutines.sample.data.user.UserLocalDataSource
import com.mvvm.koin.coroutines.sample.data.user.UserRemoteDataSource
import com.mvvm.koin.coroutines.sample.data.user.UserRepository
import org.koin.dsl.module

object RepositoriesModule {
    val module = module {

        //User Repository
        single { UserLocalDataSource(get(), get(), get()) }
        single { UserRemoteDataSource(get()) }
        single { UserRepository(get(), get(), get()) }

        //Places Repository
        single { PlacesLocalDataSource(get(),get()) }
        single { PlacesRemoteDataSource(get()) }
        single { PlacesRepository(get(), get()) }

        //Photos Repository
        single { PhotosLocalDataSource(get(),get()) }
        single { PhotosRemoteDataSource(get()) }
        single { PhotosRepository(get(), get()) }
    }
}