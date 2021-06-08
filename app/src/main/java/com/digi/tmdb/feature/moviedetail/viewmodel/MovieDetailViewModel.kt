package com.digi.tmdb.feature.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import com.digi.tmdb.feature.movielist.repo.MovieRepository

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {



    private val baseListResponse: MutableLiveData<AllListResponse> = MutableLiveData()
    val movieLiveData: LiveData<AllListResponse> = baseListResponse

    fun prepareMovieRepo(name: String) {
        movieRepository
            .getMovieListObserverRx(name)
            .subscribe {
                baseListResponse.postValue(it)
            }
    }

}