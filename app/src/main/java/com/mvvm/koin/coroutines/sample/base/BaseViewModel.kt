package com.mvvm.koin.coroutines.sample.base

import androidx.lifecycle.ViewModel
import com.mvvm.koin.coroutines.sample.coroutines.CoroutineContextProvider

abstract class BaseViewModel: ViewModel() {
    open var contextProvider: CoroutineContextProvider = CoroutineContextProvider()
}