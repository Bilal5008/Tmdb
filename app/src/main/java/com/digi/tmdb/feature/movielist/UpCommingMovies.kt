package com.digi.tmdb.feature.movielist

import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import io.reactivex.Observable

class NowPlayingMovies : ApiHelper {
    
    override fun getMovies(query: String, apiToken: String): Observable<AllListResponse> {
        return super.getMovies(query, apiToken)
    }
}