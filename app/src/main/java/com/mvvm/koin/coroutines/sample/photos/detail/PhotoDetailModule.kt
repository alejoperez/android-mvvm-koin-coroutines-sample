package com.mvvm.koin.coroutines.sample.photos.detail

import androidx.lifecycle.ViewModel
import com.mvvm.koin.coroutines.sample.di.FragmentScope
import com.mvvm.koin.coroutines.sample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PhotoDetailModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeFragment(): PhotoDetailDialogFragment

    @Binds
    @IntoMap
    @ViewModelKey(PhotoDetailViewModel::class)
    abstract fun bindViewModel(viewModel: PhotoDetailViewModel): ViewModel
}