package com.mvvm.koin.coroutines.sample.di

import com.mvvm.koin.coroutines.sample.login.LoginModule
import com.mvvm.koin.coroutines.sample.main.MainModule
import com.mvvm.koin.coroutines.sample.photos.PhotosModule
import com.mvvm.koin.coroutines.sample.photos.detail.PhotoDetailModule
import com.mvvm.koin.coroutines.sample.places.PlacesModule
import com.mvvm.koin.coroutines.sample.register.RegisterModule
import com.mvvm.koin.coroutines.sample.splash.SplashModule
import dagger.Module

@Module(
        includes = [
            SplashModule::class,
            RegisterModule::class,
            LoginModule::class,
            MainModule::class,
            PlacesModule::class,
            PhotosModule::class,
            PhotoDetailModule::class
        ]
)
abstract class ViewsModule