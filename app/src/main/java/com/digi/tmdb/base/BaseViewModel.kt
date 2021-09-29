package com.digi.tmdb.base

import androidx.lifecycle.ViewModel
import com.digi.tmdb.utils.SingleLiveData

open class BaseViewModel : ViewModel() {
    val isLoading by lazy { SingleLiveData<Boolean>().apply { value = false} }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }

}