package com.digi.tmdb.feature.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import com.digi.tmdb.feature.movielist.repo.MovieRepository

class MovieListViewModel() : ViewModel() {



    private val allListResponse: MutableLiveData<AllListResponse> = MutableLiveData()
    val movieListLiveData: LiveData<AllListResponse> = allListResponse

    fun prepareMovieListRepo(name: String) {
        MovieRepository()
            .getMovieListObserverRx(name)
            .subscribe {
                allListResponse.postValue(it)
            }
    }



    private val nowPlayingListResponse: MutableLiveData<AllListResponse> = MutableLiveData()
    val nowPlayingListLiveData: LiveData<AllListResponse> = nowPlayingListResponse

    fun prepareNowPlayingMovieRepo(name: String) {
        MovieRepository()
            .getMovieListObserverRx(name)
            .subscribe {
                nowPlayingListResponse.postValue(it)
            }
    }


    private val upcommingMovieListResponse: MutableLiveData<AllListResponse> = MutableLiveData()
    val upcommingMovieListLiveData: LiveData<AllListResponse> = upcommingMovieListResponse

    fun prepareUpCommingMovieRepo(name: String) {
        MovieRepository()
            .getMovieListObserverRx(name)
            .subscribe {
                upcommingMovieListResponse.postValue(it)
            }
    }


}