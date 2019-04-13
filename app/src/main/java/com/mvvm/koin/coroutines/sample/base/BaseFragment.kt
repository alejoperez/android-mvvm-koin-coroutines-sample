package com.mvvm.koin.coroutines.sample.base

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import com.mvvm.koin.coroutines.sample.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

abstract class BaseFragment<DB: ViewDataBinding>: Fragment(), IBaseView {

    private lateinit var fragmentContext: Context

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    lateinit var dataBinding: DB

    abstract fun getLayoutId() : Int
    abstract fun getVariablesToBind(): Map<Int,Any>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initViewModel()
        initView(inflater, container)
        return dataBinding.root
    }

    open fun initViewModel() {}

    open fun initView(inflater: LayoutInflater, container: ViewGroup?) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        dataBinding.lifecycleOwner = this
        for ((variableId,value) in getVariablesToBind()) {
            dataBinding.setVariable(variableId,value)
        }
        dataBinding.executePendingBindings()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    override fun isActive(): Boolean = isAdded

    override fun showAlert(textResource: Int) {
        activity?.let {
            if (isActive() && !it.isFinishing) {
                it.alert(textResource) {
                    yesButton { dialog -> dialog.dismiss() }
                }.show()
            }
        }
    }

    override fun getViewContext(): Context = fragmentContext

    override fun onNetworkError() = showAlert(R.string.error_network)
}