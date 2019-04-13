package com.mvvm.koin.coroutines.sample.data.room

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.mvvm.koin.coroutines.sample.base.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.inject

@ExperimentalCoroutinesApi
abstract class BaseDaoTest: BaseTest() {

    protected val dataBase by inject<SampleDataBase>()

    @Before
    fun createDb() {
        loadKoinModules(
                module(override = true) {
                    single {
                        Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, SampleDataBase::class.java)
                                .allowMainThreadQueries()
                                .build()
                    }
                }
        )
    }

    @After
    fun closeDb() {
        dataBase.close()
    }
}