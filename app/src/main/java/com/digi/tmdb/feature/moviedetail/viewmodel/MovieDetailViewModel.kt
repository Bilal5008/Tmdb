package com.digi.tmdb.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digi.tmdb.feature.moviedetail.movieresponse.MovieDetailResponse
import com.digi.tmdb.feature.movielist.ApiHelper
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import com.digi.tmdb.feature.movielist.repo.MovieRepository

class GlobalMovieViewModel() : ViewModel() {


    private val movieDetailResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val movieDetailLiveData: LiveData<MovieDetailResponse> = movieDetailResponse

    fun prepareMovieDetailRepo(name: Int) {
        MovieRepository(ApiHelper())
            .getMovieDetailObserverRx(name)
            .subscribe {
                movieDetailResponse.postValue(it)
            }
    }



    private val allListResponse: MutableLiveData<AllListResponse> = MutableLiveData()
    val movieListLiveData: LiveData<AllListResponse> = allListResponse

    fun prepareMovieListRepo(name: String) {
        MovieRepository(ApiHelper())
            .getMovieListObserverRx(name)
            .subscribe {
                allListResponse.postValue(it)
            }
    }

}