package com.digi.tmdb.feature.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import com.digi.tmdb.feature.movielist.repo.MovieRepository
import kotlinx.coroutines.*

class MovieListViewModel() : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<ArrayList<AllListResponse>>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun prepareMovieListRepo(name: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = MovieRepository().getMovieListObserverRx(name)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {

                    movieList.postValue(response.body()?.results as ArrayList<AllListResponse>)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


//    private val allListResponse: MutableLiveData<AllListResponse> = MutableLiveData()
//    val movieListLiveData: LiveData<AllListResponse> = allListResponse
//
//    fun prepareMovieListRepo(name: String) {
//        MovieRepository()
//            .getMovieListObserverRx(name)
//            .subscribe {
//                allListResponse.postValue(it)
//            }
//    }


//
//    private val nowPlayingListResponse: MutableLiveData<AllListResponse> = MutableLiveData()
//    val nowPlayingListLiveData: LiveData<AllListResponse> = nowPlayingListResponse
//
//    fun prepareNowPlayingMovieRepo(name: String) {
//        MovieRepository()
//            .getMovieListObserverRx(name)
//            .subscribe {
//                nowPlayingListResponse.postValue(it)
//            }
//    }
//
//
//    private val upcommingMovieListResponse: MutableLiveData<AllListResponse> = MutableLiveData()
//    val upcommingMovieListLiveData: LiveData<AllListResponse> = upcommingMovieListResponse
//
//    fun prepareUpCommingMovieRepo(name: String) {
//        MovieRepository()
//            .getMovieListObserverRx(name)
//            .subscribe {
//                upcommingMovieListResponse.postValue(it)
//            }
//    }


}