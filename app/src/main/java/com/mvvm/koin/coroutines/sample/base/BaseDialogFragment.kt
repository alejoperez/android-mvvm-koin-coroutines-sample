package com.mvvm.koin.coroutines.sample.base

import android.app.Dialog
import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.mvvm.koin.coroutines.sample.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

abstract class BaseDialogFragment<DB: ViewDataBinding>: DialogFragment(), IBaseView {

    private lateinit var fragmentContext: Context

    protected lateinit var dataBinding: DB

    abstract fun getLayoutId() : Int
    abstract fun getVariablesToBind(): Map<Int,Any>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initViewModel()
        initView()
        return super.onCreateDialog(savedInstanceState)
    }

    open fun initViewModel() {
    }

    open fun initView() {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getViewContext()), getLayoutId(), null,false)
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