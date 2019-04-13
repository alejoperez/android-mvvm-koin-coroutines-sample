package com.mvvm.koin.coroutines.sample.base

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mvvm.koin.coroutines.sample.R
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

abstract class BaseActivity<DB: ViewDataBinding> : AppCompatActivity(), IBaseView {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    lateinit var dataBinding: DB

    abstract fun getLayoutId(): Int
    abstract fun getVariablesToBind(): Map<Int,Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViewModel()
        initView()
    }

    open fun initViewModel() {
    }

    open fun initView() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        dataBinding.lifecycleOwner = this
        for ((variableId,value) in getVariablesToBind()) {
            dataBinding.setVariable(variableId,value)
        }
        dataBinding.executePendingBindings()
    }

    fun setToolbarTitle(textInt: Int) = toolbar.setTitle(textInt)

    override fun isActive(): Boolean = !isFinishing

    override fun showAlert(textResource: Int) {
        if (isActive()) {
            alert(textResource) {
                yesButton { dialog -> dialog.dismiss() }
            }.show()
        }
    }

    override fun getViewContext(): Context = this


    fun replaceFragment(fragment: Fragment, @IdRes fragmentId: Int, tag: String) {
        try {
            if (isNewFragment(fragmentId, tag)) {
                supportFragmentManager.beginTransaction().replace(fragmentId, fragment, tag).commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun isNewFragment(@IdRes fragmentId: Int, tag: String): Boolean = !getCurrentFragment(fragmentId)?.tag.equals(tag)

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getCurrentFragment(@IdRes fragmentId: Int): Fragment? = supportFragmentManager.findFragmentById(fragmentId)

    override fun onNetworkError() = showAlert(R.string.error_network)
}