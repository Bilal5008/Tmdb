package com.digi.tmdb.feature.movielist.viewmodel

import android.app.Activity
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import com.brainx.spotify.recievers.NetworkStateReceiver
import com.digi.tmdb.base.BaseViewModel
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import com.digi.tmdb.retrofit.AppConstants
import com.digi.tmdb.retrofit.RetroInstance
import com.digi.tmdb.retrofit.RetroService

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.HttpException

class ArtistViewModel : BaseViewModel(), NetworkStateReceiver.NetworkStateReceiverListener {
    private var activity: Activity? = null




    private var movieListLiveData: MutableLiveData<AllListResponse> = MutableLiveData()
    private var queryString = ""
    private var networkStateReceiver: NetworkStateReceiver = NetworkStateReceiver()

    private var isNetworkAvailable = false
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getMovieData(ownerActivity: Activity): MutableLiveData<AllListResponse> {
        activity = ownerActivity
        initNetworkReciever()
        return movieListLiveData
    }

    fun makeMovieApiCall(query: String) {
        queryString = query
        isLoading.postValue(true)

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)

        retroInstance.getMovies(query, AppConstants.API_TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMovieListObserverRx())

    }


    private fun getMovieListObserverRx(): Observer<AllListResponse> {
        return object : Observer<AllListResponse> {
            override fun onComplete() = Unit

            override fun onError(throwable: Throwable) {
                if (isNetworkAvailable) {

                    isLoading.postValue(false)
                    if (throwable is HttpException) {
//                        artistsData.postValue(
//                            ArtistData(
//                                errorCode = throwable.code(),
//                                errorMessage = when (throwable.code()) {
////                                    AppConstants.INVALID_IDS_CODE ->
////                                        activity?.getString(R.string.invalid_ids)
////                                    AppConstants.INVALID_TOKEN ->
////                                        activity?.getString(R.string.token_expired)
////                                    else ->
////                                        AppConstants.EMPTY_STRING
//                                }
//                            )
//                        )
                    }
                }
            }

            override fun onNext(allListResponse: AllListResponse) {
                isLoading.postValue(false)
                movieListLiveData.postValue(allListResponse)

            }

            override fun onSubscribe(d: Disposable) = Unit
        }
    }




    override fun networkAvailable() {
        isNetworkAvailable = true
    }

    override fun networkUnavailable() {
        isNetworkAvailable = false
    }

    private fun initNetworkReciever() {

        activity?.registerReceiver(
            networkStateReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        networkStateReceiver.addListener(this)
    }

    override fun onCleared() {

        networkStateReceiver.removeListener(this)
        activity?.unregisterReceiver(networkStateReceiver)
        super.onCleared()
    }
}