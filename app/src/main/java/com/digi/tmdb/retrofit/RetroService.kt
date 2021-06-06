package com.brainx.spotify.retrofit

import com.brainx.spotify.data.models.ArtistData
import com.brainx.spotify.data.models.listResponse.AllListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface RetroService {

    @GET("/v1/artists")
    fun getArtistList(
        @Query("ids") id: String,
        @Header("Authorization") authHeader: String
    ): Observable<ArtistData>


    @GET("movie/{filter}")
    fun getMovies(
        @Path("filter") filter: String,
        @Query("api_key") apiKey: String
    ): Observable<AllListResponse>
}