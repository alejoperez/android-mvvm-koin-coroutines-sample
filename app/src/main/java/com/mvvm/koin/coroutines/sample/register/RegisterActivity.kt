package com.mvvm.koin.coroutines.sample.register

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.mvvm.koin.coroutines.sample.BR
import com.mvvm.koin.coroutines.sample.R
import com.mvvm.koin.coroutines.sample.base.BaseActivity
import com.mvvm.koin.coroutines.sample.databinding.ActivityRegisterBinding
import com.mvvm.koin.coroutines.sample.livedata.Event
import com.mvvm.koin.coroutines.sample.livedata.Status
import com.mvvm.koin.coroutines.sample.login.LoginActivity
import com.mvvm.koin.coroutines.sample.main.MainActivity
import com.mvvm.koin.coroutines.sample.utils.EditTextUtils
import com.mvvm.koin.coroutines.sample.webservice.RegisterResponse
import org.jetbrains.anko.startActivity
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    val viewModel by currentScope.viewModel<RegisterViewModel>(this)

    override fun getLayoutId() = R.layout.activity_register

    override fun getVariablesToBind(): Map<Int, Any> = mapOf(
            BR.viewModel to viewModel,
            BR.etUtils to EditTextUtils
    )

    override fun initViewModel() {
        super.initViewModel()
        viewModel.registerEvent.observe(this, onRegisterResponseObserver)
    }

    override fun initView() {
        super.initView()
        dataBinding.tvGoToLogin.setOnClickListener { startActivity<LoginActivity>() }
    }

    private val onRegisterResponseObserver = Observer<Event<RegisterResponse>> { onRegisterResponse(it) }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onRegisterResponse(response: Event<RegisterResponse>) {
        when(response.status) {
            Status.SUCCESS -> onRegisterSuccess()
            Status.FAILURE -> onRegisterFailure()
            Status.NETWORK_ERROR -> onNetworkError()
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onRegisterSuccess() {
        startActivity<MainActivity>()
        finishAffinity()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onRegisterFailure() = showAlert(R.string.error_user_already_exists)

}
