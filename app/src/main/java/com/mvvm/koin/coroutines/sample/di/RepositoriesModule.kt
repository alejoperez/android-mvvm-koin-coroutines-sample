package com.mvvm.koin.coroutines.sample.di

import com.mvvm.koin.coroutines.sample.data.photos.PhotosRepositoryModule
import com.mvvm.koin.coroutines.sample.data.places.PlacesRepositoryModule
import com.mvvm.koin.coroutines.sample.data.user.UserRepositoryModule
import dagger.Module

@Module(
        includes = [
            UserRepositoryModule::class,
            PlacesRepositoryModule::class,
            PhotosRepositoryModule::class
        ]
)
abstract class RepositoriesModule