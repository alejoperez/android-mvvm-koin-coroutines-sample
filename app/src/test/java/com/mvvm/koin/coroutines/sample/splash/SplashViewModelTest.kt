package com.mvvm.koin.coroutines.sample.splash

import com.mvvm.koin.coroutines.sample.base.BaseTest
import com.mvvm.koin.coroutines.sample.data.user.UserRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock

@ExperimentalCoroutinesApi
class SplashViewModelTest: BaseTest() {

    private val userRepository = declareMock<UserRepository>()
    private val viewModel by inject<SplashViewModel>()

    @Test
    fun userLoggedEventTrueTest() {
        whenever(userRepository.isLoggedIn()).then { true }
        Assert.assertTrue("user should be logged in",viewModel.isUserLoggedIn())
    }

    @Test
    fun userLoggedEventFalseTest() {
        whenever(userRepository.isLoggedIn()).then { false }
        Assert.assertFalse("user shouldn't be logged in",viewModel.isUserLoggedIn())

    }

}