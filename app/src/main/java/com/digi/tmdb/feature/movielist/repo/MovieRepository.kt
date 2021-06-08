package com.digi.tmdb.feature.movielist.repo

import android.util.Log
import com.digi.tmdb.feature.movielist.ApiHelper
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import com.digi.tmdb.retrofit.AppConstants
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DefaultObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MovieRepository(var apiHelper: ApiHelper) {
    var TAG = "MovieRepository"


    fun getMovieListObserverRx(query: String): Observable<AllListResponse> {
        return Observable.create { emitter ->
            apiHelper.run {
                getMovies(query, AppConstants.API_TOKEN).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<AllListResponse> {
                        override fun onNext(t: AllListResponse) {
                            emitter.onNext(t)
                        }

                        override fun onError(e: Throwable) {
                            if (e is HttpException) {
                                val httpException: HttpException = e as HttpException
                                when {
                                    httpException.code() === 400 -> Log.d(
                                        TAG,
                                        "onError: BAD REQUEST"
                                    )
                                    httpException.code() === 401 -> Log.d(
                                        TAG,
                                        "onError: NOT AUTHORIZED"
                                    )
                                    httpException.code() === 403 -> Log.d(
                                        TAG,
                                        "onError: FORBIDDEN"
                                    )
                                    httpException.code() === 404 -> Log.d(
                                        TAG,
                                        "onError: NOT FOUND"
                                    )
                                    httpException.code() === 500 -> Log.d(
                                        TAG,
                                        "onError: INTERNAL SERVER ERROR"
                                    )
                                    httpException.code() === 502 -> Log.d(
                                        TAG,
                                        "onError: BAD GATEWAY"
                                    )
                                }
                            }
                        }

                        override fun onComplete() {
                            Log.d(
                                TAG,
                                "onComplete"
                            )
                        }

                        override fun onSubscribe(d: Disposable) {

                        }


                    })
            }
        }
    }
}

//                .subscribe({
//                    if (it != null) {
//
//                        emitter.onNext(it)
//                    }
//                }, {
//                    it.printStackTrace()
//                })


//            override fun onComplete() = Unit
//
//            override fun onError(throwable: Throwable) {
//                if (isNetworkAvailable) {
//
//                    isLoading.postValue(false)
//                    if (throwable is HttpException) {
////                        artistsData.postValue(
////                            ArtistData(
////                                errorCode = throwable.code(),
////                                errorMessage = when (throwable.code()) {
//////                                    AppConstants.INVALID_IDS_CODE ->
//////                                        activity?.getString(R.string.invalid_ids)
//////                                    AppConstants.INVALID_TOKEN ->
//////                                        activity?.getString(R.string.token_expired)
//////                                    else ->
//////                                        AppConstants.EMPTY_STRING
////                                }
////                            )
////                        )
//                    }
//                }
//            }
//
//            override fun onNext(allListResponse: AllListResponse) {
//                isLoading.postValue(false)
//                movieListLiveData.postValue(allListResponse)
//
//            }
//
//            override fun onSubscribe(d: Disposable) = Unit
//        }
//    }

