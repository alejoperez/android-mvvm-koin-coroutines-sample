package com.mvvm.koin.coroutines.sample.data.user

import com.mvvm.koin.coroutines.sample.coroutines.ICoroutineContextProvider
import com.mvvm.koin.coroutines.sample.data.room.User
import com.mvvm.koin.coroutines.sample.data.preference.PreferenceManager
import com.mvvm.koin.coroutines.sample.data.room.UserDao
import com.mvvm.koin.coroutines.sample.webservice.LoginRequest
import com.mvvm.koin.coroutines.sample.webservice.LoginResponse
import com.mvvm.koin.coroutines.sample.webservice.RegisterRequest
import com.mvvm.koin.coroutines.sample.webservice.RegisterResponse
import kotlinx.coroutines.*

class UserLocalDataSource(private val userDao: UserDao, private val preferenceManager: PreferenceManager, private val provider: ICoroutineContextProvider): IUserDataSource {

    override suspend fun getUserAsync(): Deferred<User> = CoroutineScope(provider.getIoContext()).async { userDao.getUser() }

    override suspend fun saveUserAsync(user: User) = withContext(provider.getIoContext()) { userDao.saveUser(user) }

    override suspend fun loginAsync(request: LoginRequest): Deferred<LoginResponse> = throw UnsupportedOperationException()

    override suspend fun registerAsync(request: RegisterRequest): Deferred<RegisterResponse> = throw UnsupportedOperationException()

    override fun isLoggedIn(): Boolean = preferenceManager.findPreference(PreferenceManager.ACCESS_TOKEN,"").isNotEmpty()

    override fun logout() = preferenceManager.putPreference(PreferenceManager.ACCESS_TOKEN,"")

}