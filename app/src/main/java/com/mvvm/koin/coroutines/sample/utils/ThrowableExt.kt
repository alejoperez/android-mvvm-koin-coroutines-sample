package com.mvvm.koin.coroutines.sample.utils

import com.mvvm.koin.coroutines.sample.livedata.Event
import retrofit2.HttpException


fun <T> Throwable.getEventError(): Event<T> {
    return when (this) {
        is HttpException -> Event.failure()
        else -> Event.networkError()
    }
}