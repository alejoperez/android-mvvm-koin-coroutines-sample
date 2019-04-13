package com.mvvm.koin.coroutines.sample.base

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mvvm.koin.coroutines.sample.coroutines.ICoroutineContextProvider
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
abstract class BaseTest: KoinTest {

    private val coroutineModule = module(override = true) {
        single<ICoroutineContextProvider> { TestCoroutineContextProvider() }
    }

    @Before
    fun setUp() {
        loadKoinModules(coroutineModule)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    fun <T> T.toDeferred() = CompletableDeferred(this)
}