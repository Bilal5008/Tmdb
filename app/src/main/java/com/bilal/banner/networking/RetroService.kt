package com.bilal.banner.networking

import com.bilal.banner.feature.bannerstatus.bannerstatusresponse.AllListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetroService {


    @GET("movie/{filter}")
    suspend fun getMovies(
        @Path("filter") filter: String,
        @Query("api_key") apiKey: String
    ): Response<AllListResponse>


}