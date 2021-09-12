package com.digi.tmdb.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digi.tmdb.feature.moviedetail.viewmodel.MovieDetailViewModel
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel
import com.digi.tmdb.feature.repo.MainRepository
import javax.inject.Inject

class GlobalViewModelFactory @Inject constructor(
    private val mainRepository: MainRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel(mainRepository) as T
            }
            MovieDetailViewModel::class.java -> {
                MovieDetailViewModel(mainRepository) as T
            }
            else -> {
                super.create(modelClass)
            }
        }
    }


}