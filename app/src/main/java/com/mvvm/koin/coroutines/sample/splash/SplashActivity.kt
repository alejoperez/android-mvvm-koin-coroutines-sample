package com.mvvm.koin.coroutines.sample.splash

import android.os.Bundle
import android.os.Handler
import androidx.annotation.VisibleForTesting
import com.mvvm.koin.coroutines.sample.R
import com.mvvm.koin.coroutines.sample.base.BaseActivity
import com.mvvm.koin.coroutines.sample.databinding.ActivitySplashBinding
import com.mvvm.koin.coroutines.sample.main.MainActivity
import com.mvvm.koin.coroutines.sample.register.RegisterActivity
import org.jetbrains.anko.startActivity
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    companion object {
        const val SPLASH_DELAY = 2000L
    }

    override fun getLayoutId(): Int = R.layout.activity_splash
    override fun getVariablesToBind(): Map<Int, Any> = emptyMap()

    val viewModel by currentScope.viewModel<SplashViewModel>(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({ goToNextScreen(viewModel.isUserLoggedIn()) }, SPLASH_DELAY)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun goToNextScreen(isLoggedIn: Boolean) {
        if (isLoggedIn) {
            startActivity<MainActivity>()
        } else {
            startActivity<RegisterActivity>()
        }
        finish()
    }

}
