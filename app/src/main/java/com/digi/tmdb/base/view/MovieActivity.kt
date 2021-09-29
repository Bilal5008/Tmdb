package com.digi.tmdb.base.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.digi.tmdb.R
import com.digi.tmdb.base.BaseActivity
import com.digi.tmdb.base.BaseViewModel
import com.digi.tmdb.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : BaseActivity<ActivityMovieBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel by viewModels()
    override val layoutId: Int = R.layout.activity_movie


}