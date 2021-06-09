package com.digi.tmdb.api_interface

interface IPopularMovies {
    fun getMovies(query: String, apiToken: String)
}