package com.digi.tmdb.feature.movielist.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digi.tmdb.feature.movielist.ApiHelper
import com.digi.tmdb.feature.movielist.repo.MovieRepository
import com.digi.tmdb.feature.movielist.viewmodel.MovieViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieViewModel::class.java!!)) {
            MovieViewModel(MovieRepository(apiHelper)) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}