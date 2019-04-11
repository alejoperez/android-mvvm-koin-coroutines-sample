package com.mvvm.koin.coroutines.sample.login

import androidx.lifecycle.ViewModel
import com.mvvm.koin.coroutines.sample.di.ActivityScope
import com.mvvm.koin.coroutines.sample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeActivity(): LoginActivity

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindViewModel(viewModel: LoginViewModel): ViewModel

}