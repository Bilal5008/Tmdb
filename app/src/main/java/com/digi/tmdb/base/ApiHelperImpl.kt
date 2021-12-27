package com.digi.tmdb.base

import com.digi.tmdb.feature.moviedetail.movieresponse.MovieDetailResponse
import com.digi.tmdb.feature.movielist.listResponse.AllListResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
  private val apiService: ApiService
) : ApiHelper {
  override suspend fun getMovies(filter: String, apiKey: String): Response<AllListResponse> =
    apiService.getMovies(filter, apiKey)

  override suspend fun getMoviesDetails(
    filter: Int,
    apiKey: String
  ): Response<MovieDetailResponse> = apiService.getMoviesDetails(filter, apiKey)

}