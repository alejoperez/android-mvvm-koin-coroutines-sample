package com.mvvm.koin.coroutines.sample.base

import androidx.lifecycle.ViewModel
import com.mvvm.koin.coroutines.sample.coroutines.ICoroutineContextProvider
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel: ViewModel(), KoinComponent {
    val contextProvider by inject<ICoroutineContextProvider>()
}