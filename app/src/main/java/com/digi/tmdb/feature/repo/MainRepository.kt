package com.digi.tmdb.feature.repo

import android.util.Log
import com.digi.tmdb.base.ApiHelper


import com.digi.tmdb.utils.AppConstants
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    var TAG = "MovieRepository"
    suspend fun getMovieListObserverRx(query: String) =
        apiHelper.getMovies(query, AppConstants.API_TOKEN)


//    fun getMovieListObserverRx(query: String): Observable<AllListResponse> {
//        return Observable.create { emitter ->
//            RetroInstance.apiService.getMovies(query, AppConstants.API_TOKEN)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Observer<AllListResponse> {
//                    override fun onNext(t: AllListResponse) {
//                        emitter.onNext(t)
//                    }
//
//                    override fun onError(e: Throwable) {
//                        if (e is HttpException) {
//                            val httpException: HttpException = e
//                            when {
//                                httpException.code() == 400 -> Log.d(
//                                    TAG,
//                                    "onError: BAD REQUEST"
//                                )
//                                httpException.code() == 401 -> Log.d(
//                                    TAG,
//                                    "onError: NOT AUTHORIZED"
//                                )
//                                httpException.code() == 403 -> Log.d(
//                                    TAG,
//                                    "onError: FORBIDDEN"
//                                )
//                                httpException.code() == 404 -> Log.d(
//                                    TAG,
//                                    "onError: NOT FOUND"
//                                )
//                                httpException.code() == 500 -> Log.d(
//                                    TAG,
//                                    "onError: INTERNAL SERVER ERROR"
//                                )
//                                httpException.code() == 502 -> Log.d(
//                                    TAG,
//                                    "onError: BAD GATEWAY"
//                                )
//                            }
//                        }
//                    }
//
//                    override fun onComplete() {
//                        Log.d(
//                            TAG,
//                            "onComplete"
//                        )
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                        Log.d(
//                            TAG,
//                            "onSubscribe"
//                        )
//                    }
//
//
//                })
//
//        }
//    }

}
