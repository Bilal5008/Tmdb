package com.digi.tmdb.feature.repo


import com.digi.tmdb.base.ApiHelper
import com.digi.tmdb.utils.AppConstants
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    var TAG = "MovieRepository"
    suspend fun getMovieListObserverRx(query: String) =
        apiHelper.getMovies(query, AppConstants.API_TOKEN)

    suspend fun getMovieDetailObserverRx(query: Int) =
        apiHelper.getMoviesDetails(query, AppConstants.API_TOKEN)

}
