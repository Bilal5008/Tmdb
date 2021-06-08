package com.digi.tmdb.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digi.tmdb.feature.moviedetail.viewmodel.MovieDetailViewModel
import com.digi.tmdb.feature.movielist.ApiHelper
import com.digi.tmdb.feature.movielist.repo.MovieRepository
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel

class GlobalViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel(MovieRepository(apiHelper)) as T
            }
            MovieDetailViewModel::class.java -> {
                MovieDetailViewModel(MovieRepository(apiHelper)) as T
            }
            else -> {
                super.create(modelClass)
            }
        }
    }


}