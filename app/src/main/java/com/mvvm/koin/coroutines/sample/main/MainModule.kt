package com.mvvm.koin.coroutines.sample.main

import androidx.lifecycle.ViewModel
import com.mvvm.koin.coroutines.sample.di.ActivityScope
import com.mvvm.koin.coroutines.sample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel

}