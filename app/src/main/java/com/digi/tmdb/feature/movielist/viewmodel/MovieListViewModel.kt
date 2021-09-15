package com.digi.tmdb.feature.movielist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import com.digi.tmdb.feature.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
     var mquery  = MutableLiveData<String>()

    private val errorMessage = MutableLiveData<String>()
    private val _movieList = MutableLiveData<ArrayList<AllListResponse>>()
    val mutableMovieList: MutableLiveData<ArrayList<AllListResponse>>
        get() = _movieList
    var job: Job? = null

    fun prepareMovieListRepo(name: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getMovieListObserverRx(name)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {

                    _movieList.postValue(response.body()?.results as ArrayList<AllListResponse>)
//                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
//        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}