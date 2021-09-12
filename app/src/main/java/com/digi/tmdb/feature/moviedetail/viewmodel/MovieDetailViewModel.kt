package com.digi.tmdb.feature.moviedetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digi.tmdb.feature.moviedetail.movieresponse.MovieDetailResponse
import com.digi.tmdb.feature.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val _movieDetailResponse = MutableLiveData<MovieDetailResponse>()
    val movieDetailLiveData: MutableLiveData<MovieDetailResponse>
    get() = _movieDetailResponse

    fun prepareMovieDetailRepo(name: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getMovieDetailObserverRx(name)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {

                    _movieDetailResponse.postValue(response.body() as MovieDetailResponse)
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