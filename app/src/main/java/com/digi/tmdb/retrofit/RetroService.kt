package com.digi.tmdb.retrofit

import com.digi.tmdb.feature.moviedetail.movieresponse.MovieDetailResponse
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetroService {


    @GET("movie/{filter}")
    fun getMovies(
        @Path("filter") filter: String,
        @Query("api_key") apiKey: String
    ): Observable<AllListResponse>

    @GET("movie/{movie_id}")
    fun getMoviesDetails(
        @Path("movie_id") filter: Int,
        @Query("api_key") apiKey: String
    ): Observable<MovieDetailResponse>
}