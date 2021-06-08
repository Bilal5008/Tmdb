package com.digi.tmdb.feature.movielist

import com.digi.tmdb.retrofit.RetroService

class ApiHelper(private val apiService: RetroService) {

     fun getMovies(query: String, apiToken: String) = apiService.getMovies(query,apiToken)
}