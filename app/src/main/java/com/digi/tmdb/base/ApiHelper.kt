package com.digi.tmdb.base

import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {

    suspend fun getMovies(
        @Path("filter") filter: String,
        @Query("api_key") apiKey: String
    ): Response<AllListResponse>
}