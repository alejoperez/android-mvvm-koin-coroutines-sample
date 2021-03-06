package com.mvvm.koin.coroutines.sample.login

import androidx.annotation.VisibleForTesting
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.mvvm.koin.coroutines.sample.R
import com.mvvm.koin.coroutines.sample.base.BaseActivity
import com.mvvm.koin.coroutines.sample.databinding.ActivityLoginBinding
import com.mvvm.koin.coroutines.sample.livedata.Event
import com.mvvm.koin.coroutines.sample.livedata.Status
import com.mvvm.koin.coroutines.sample.main.MainActivity
import com.mvvm.koin.coroutines.sample.utils.EditTextUtils
import com.mvvm.koin.coroutines.sample.webservice.LoginResponse
import org.jetbrains.anko.startActivity
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    val viewModel by currentScope.viewModel<LoginViewModel>(this)

    override fun getLayoutId(): Int = R.layout.activity_login
    override fun getVariablesToBind(): Map<Int, Any> = mapOf(
            BR.viewModel to viewModel,
            BR.etUtils to EditTextUtils
    )

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loginEvent.observe(this, onLoginResponseObserver)
    }

    private val onLoginResponseObserver = Observer<Event<LoginResponse>> { onLoginResponse(it) }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onLoginResponse(response: Event<LoginResponse>) {
        when (response.status) {
            Status.SUCCESS -> onLoginSuccess()
            Status.FAILURE -> onLoginFailure()
            Status.NETWORK_ERROR -> onNetworkError()
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onLoginSuccess() {
        startActivity<MainActivity>()
        finishAffinity()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onLoginFailure() = showAlert(R.string.error_invalid_credentials)
}
