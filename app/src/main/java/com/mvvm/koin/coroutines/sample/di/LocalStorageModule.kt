package com.mvvm.koin.coroutines.sample.di

import com.mvvm.koin.coroutines.sample.data.preference.PreferenceManager
import com.mvvm.koin.coroutines.sample.data.room.SampleDataBase
import org.koin.dsl.module

object LocalStorageModule {

    val module = module {
        // SharedPreference
        single { PreferenceManager(get()) }

        //Rooom
        single { SampleDataBase.buildDatabase(get()) }
        single { get<SampleDataBase>().userDao() }
        single { get<SampleDataBase>().photoDao() }
        single { get<SampleDataBase>().placeDao() }
    }

}