package com.mvvm.koin.coroutines.sample



import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mvvm.koin.coroutines.sample.splash.SplashActivity
import com.mvvm.koin.coroutines.sample.utils.EditTextUtils
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        EditTextUtils.getOnlyNumbersFilters()
        assertEquals("com.mvvm.koin.coroutines.sample", appContext.packageName)

    }
}
