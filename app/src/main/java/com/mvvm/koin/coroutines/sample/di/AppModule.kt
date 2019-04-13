package com.mvvm.koin.coroutines.sample.di

object AppModule {

    val module = listOf(
            CoroutinesModule.module,
            ViewsModule.module,
            RepositoriesModule.module,
            LocalStorageModule.module,
            WebServiceModule.module)
}







