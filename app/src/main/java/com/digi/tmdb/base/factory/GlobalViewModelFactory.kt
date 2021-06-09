package com.digi.tmdb.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digi.tmdb.feature.moviedetail.viewmodel.MovieDetailViewModel
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel

class GlobalViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel() as T
            }
            MovieDetailViewModel::class.java -> {
                MovieDetailViewModel() as T
            }
            else -> {
                super.create(modelClass)
            }
        }
    }


}