package com.bilal.banner.feature.bannerstatus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bilal.banner.feature.bannerstatus.bannerstatusresponse.AllListResponse
import com.bilal.banner.feature.bannerstatus.repo.BannerRepository
import kotlinx.coroutines.*

class BannerViewModel() : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<ArrayList<AllListResponse>>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun prepareMovieListRepo(name: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = BannerRepository().getMovieListObserverRx(name)
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


}