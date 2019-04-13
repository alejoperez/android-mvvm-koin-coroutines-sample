package com.mvvm.koin.coroutines.sample.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvvm.koin.coroutines.sample.base.BaseViewModel
import com.mvvm.koin.coroutines.sample.data.room.User
import com.mvvm.koin.coroutines.sample.data.user.UserRepository
import com.mvvm.koin.coroutines.sample.livedata.Event
import com.mvvm.koin.coroutines.sample.utils.getEventError
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val user = MutableLiveData<Event<User>>()

    fun getUser() {
        viewModelScope.launch(contextProvider.getMainContext()) {
            try {
                val response = withContext(contextProvider.getIoContext()) { userRepository.getUserAsync().await() }
                user.value = Event.success(response)
            } catch (e: Exception) {
                user.value = e.getEventError()
            }
        }
    }

    fun logout() = userRepository.logout()

}