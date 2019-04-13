package com.mvvm.koin.coroutines.sample.di

import com.mvvm.koin.coroutines.sample.login.LoginActivity
import com.mvvm.koin.coroutines.sample.login.LoginViewModel
import com.mvvm.koin.coroutines.sample.main.MainActivity
import com.mvvm.koin.coroutines.sample.main.MainViewModel
import com.mvvm.koin.coroutines.sample.photos.PhotosFragment
import com.mvvm.koin.coroutines.sample.photos.PhotosViewModel
import com.mvvm.koin.coroutines.sample.photos.detail.PhotoDetailDialogFragment
import com.mvvm.koin.coroutines.sample.photos.detail.PhotoDetailViewModel
import com.mvvm.koin.coroutines.sample.places.PlacesFragment
import com.mvvm.koin.coroutines.sample.places.PlacesViewModel
import com.mvvm.koin.coroutines.sample.register.RegisterActivity
import com.mvvm.koin.coroutines.sample.register.RegisterViewModel
import com.mvvm.koin.coroutines.sample.splash.SplashActivity
import com.mvvm.koin.coroutines.sample.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object ViewsModule {
    val module = module {

        //Splash
        scope(named<SplashActivity>()) {
            viewModel { SplashViewModel(get()) }
        }

        //Register
        scope(named<RegisterActivity>()) {
            viewModel { RegisterViewModel(get()) }
        }

        //Login
        scope(named<LoginActivity>()) {
            viewModel { LoginViewModel(get()) }
        }

        //Main
        scope(named<MainActivity>()) {
            viewModel { MainViewModel(get()) }
        }

        //Places
        scope(named<PlacesFragment>()) {
            viewModel { PlacesViewModel(get()) }
        }

        //Photos
        scope(named<PhotosFragment>()) {
            viewModel { PhotosViewModel(get()) }
        }

        //PhotoDetail
        scope(named<PhotoDetailDialogFragment>()) {
            viewModel { PhotoDetailViewModel() }
        }
    }

}