package com.mvvm.koin.coroutines.sample.login

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvvm.koin.coroutines.sample.R
import com.mvvm.koin.coroutines.sample.base.BaseViewModel
import com.mvvm.koin.coroutines.sample.data.user.UserRepository
import com.mvvm.koin.coroutines.sample.databinding.BindingAdapters
import com.mvvm.koin.coroutines.sample.livedata.Event
import com.mvvm.koin.coroutines.sample.utils.*
import com.mvvm.koin.coroutines.sample.webservice.LoginRequest
import com.mvvm.koin.coroutines.sample.webservice.LoginResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val userRepository: UserRepository): BaseViewModel() {

    val email = ObservableField("")
    val password = ObservableField("")

    val emailError = ObservableInt(BindingAdapters.EMPTY)
    val passwordError = ObservableInt(BindingAdapters.EMPTY)

    val isLoading = ObservableBoolean(false)

    val loginEvent = MutableLiveData<Event<LoginResponse>>()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun isValidEmail(): Boolean = email.getValueOrDefault().isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email.getValueOrDefault()).matches()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun isValidPassword(): Boolean = password.getValueOrDefault().isNotEmpty()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun isValidForm(): Boolean = isValidEmail() && isValidPassword()

    fun login() {
        if (isValidForm()) {

            viewModelScope.launch(contextProvider.getMainContext()) {

                try {
                    showProgress()
                    val response = withContext(contextProvider.getIoContext()) {
                        val loginRequest = LoginRequest(email.getValueOrDefault(), password.getValueOrDefault())
                        userRepository.loginAsync(loginRequest).await()

                    }
                    loginEvent.value = Event.success(response)
                } catch (t: Throwable) {
                    loginEvent.value = t.getEventError()

                } finally {
                    hideProgress()
                }
            }

        } else {
            emailError.checkField(R.string.error_invalid_email,isValidEmail())
            passwordError.checkField(R.string.error_empty_password,isValidPassword())
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun showProgress() = isLoading.set(true)

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun hideProgress() = isLoading.set(false)
}