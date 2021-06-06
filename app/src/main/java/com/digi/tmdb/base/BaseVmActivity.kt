package com.brainx.spotify.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract val mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)
    
    private var requestCode: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind()
        init(savedInstanceState)
    }


    private fun init(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
    }

    open fun initDataBind() {}
}