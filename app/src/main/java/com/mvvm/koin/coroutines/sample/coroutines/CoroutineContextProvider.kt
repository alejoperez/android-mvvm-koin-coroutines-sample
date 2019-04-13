package com.mvvm.koin.coroutines.sample.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface ICoroutineContextProvider {
    fun getMainContext() : CoroutineContext
    fun getIoContext() : CoroutineContext
}

class CoroutineContextProvider: ICoroutineContextProvider {

    val Main by lazy { getMainContext() }

    val IO by lazy { getIoContext() }

    override fun getMainContext() = Dispatchers.Main

    override fun getIoContext() = Dispatchers.IO

}