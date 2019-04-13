package com.mvvm.koin.coroutines.sample.application

import android.app.Application
import com.mvvm.koin.coroutines.sample.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class SampleApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@SampleApp)
            modules(AppModule.module)
        }
    }

}