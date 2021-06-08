package com.digi.tmdb.feature.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digi.tmdb.feature.moviedetail.movieresponse.MovieDetailResponse
import com.digi.tmdb.feature.movielist.repo.MovieRepository

class MovieDetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {


    private val baseListResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val movieLiveData: LiveData<MovieDetailResponse> = baseListResponse

    fun prepareDetailMovieRepo(name: Int) {
        movieRepository
            .getMovieDetailObserverRx(name)
            .subscribe {
                baseListResponse.postValue(it)
            }
    }

}