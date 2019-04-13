package com.mvvm.koin.coroutines.sample.di

import com.mvvm.koin.coroutines.sample.base.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.koin.test.check.checkModules

@ExperimentalCoroutinesApi
class AppModuleTest : BaseTest() {

    @Test
    fun testDependenceInjectionTree() {
        getKoin().checkModules()
    }

}