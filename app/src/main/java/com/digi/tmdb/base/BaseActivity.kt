package com.digi.tmdb.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.digi.tmdb.BR
import com.digi.tmdb.databinding.ActivityMovieBinding

abstract class BaseActivity<ViewBinding : ActivityMovieBinding, viewModel : BaseViewModel> :
    AppCompatActivity() {

    private lateinit var viewBinding: ViewBinding
    protected abstract val viewModel: viewModel

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.apply {
            setVariable(BR.viewModel, viewModel)
            viewBinding.lifecycleOwner = this@BaseActivity
            root.isClickable = true
            executePendingBindings()
        }


    }
}