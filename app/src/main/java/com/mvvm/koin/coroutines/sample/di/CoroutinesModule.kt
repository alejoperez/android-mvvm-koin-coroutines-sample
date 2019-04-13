package com.mvvm.koin.coroutines.sample.di

import com.mvvm.koin.coroutines.sample.coroutines.CoroutineContextProvider
import com.mvvm.koin.coroutines.sample.coroutines.ICoroutineContextProvider
import org.koin.dsl.module


object CoroutinesModule {

    val module = module {
        // Coroutine Context
        single<ICoroutineContextProvider> { CoroutineContextProvider() }
    }
}