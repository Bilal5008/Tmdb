package com.digi.tmdb.base

import com.digi.tmdb.feature.moviedetail.movieresponse.MovieDetailResponse
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{filter}")
    suspend fun getMovies(
        @Path("filter") filter: String,
        @Query("api_key") apiKey: String
    ): Response<AllListResponse>

    @GET("movie/{movie_id}")
    suspend  fun getMoviesDetails(
        @Path("movie_id") filter: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailResponse>



}