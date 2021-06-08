package com.digi.tmdb.feature.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digi.tmdb.feature.movielist.repo.MovieRepository
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel


class ViewModelListFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            MovieListViewModel(MovieRepository(apiHelper)) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}