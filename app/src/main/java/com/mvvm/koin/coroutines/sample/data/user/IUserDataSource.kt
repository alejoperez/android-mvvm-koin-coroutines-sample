package com.mvvm.koin.coroutines.sample.data.user

import com.mvvm.koin.coroutines.sample.data.room.User
import com.mvvm.koin.coroutines.sample.webservice.LoginRequest
import com.mvvm.koin.coroutines.sample.webservice.LoginResponse
import com.mvvm.koin.coroutines.sample.webservice.RegisterRequest
import com.mvvm.koin.coroutines.sample.webservice.RegisterResponse
import kotlinx.coroutines.Deferred

interface IUserDataSource {

    suspend fun getUserAsync(): Deferred<User>

    suspend fun saveUserAsync(user: User)

    suspend fun loginAsync(request: LoginRequest): Deferred<LoginResponse>

    suspend fun registerAsync(request: RegisterRequest): Deferred<RegisterResponse>

    fun isLoggedIn(): Boolean

    fun logout()
}