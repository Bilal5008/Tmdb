package com.digi.tmdb.feature.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digi.tmdb.feature.moviedetail.movieresponse.MovieDetailResponse

class MovieDetailViewModel() : ViewModel() {


    private val movieDetailResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val movieDetailLiveData: LiveData<MovieDetailResponse> = movieDetailResponse

    fun prepareMovieDetailRepo(name: Int) {
//        MovieRepository()
//            .getMovieDetailObserverRx(name)
//            .subscribe {
//                movieDetailResponse.postValue(it)
//            }
    }




}