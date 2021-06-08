package com.digi.tmdb.feature.view

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.digi.tmdb.R
import com.digi.tmdb.databinding.MovieListActivityBinding
import com.digi.tmdb.feature.movielist.ApiHelper
import com.digi.tmdb.feature.movielist.adapter.MovieListAdapter
import com.digi.tmdb.feature.movielist.factory.ViewModelFactory
import com.digi.tmdb.feature.movielist.viewmodel.MovieViewModel
import com.digi.tmdb.retrofit.Filter
import com.digi.tmdb.retrofit.RetroInstance

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }


}