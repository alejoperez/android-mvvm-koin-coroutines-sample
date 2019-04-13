package com.mvvm.koin.coroutines.sample.base

import com.mvvm.koin.coroutines.sample.coroutines.ICoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TestCoroutineContextProvider : ICoroutineContextProvider {

    val Main by lazy { getMainContext() }

    val IO by lazy { getIoContext() }

    override fun getMainContext() = Dispatchers.Unconfined

    override fun getIoContext() = Dispatchers.Unconfined
}