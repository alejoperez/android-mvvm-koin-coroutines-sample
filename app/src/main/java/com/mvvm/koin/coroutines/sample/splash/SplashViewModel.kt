package com.mvvm.koin.coroutines.sample.splash

import com.mvvm.koin.coroutines.sample.base.BaseViewModel
import com.mvvm.koin.coroutines.sample.data.user.UserRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {

    fun isUserLoggedIn() = userRepository.isLoggedIn()

}