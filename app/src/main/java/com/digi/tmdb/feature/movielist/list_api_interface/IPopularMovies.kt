package com.digi.tmdb.feature.movielist.list_api_interface

interface IPopularMovies {
    fun getMovies(query: String, apiToken: String)
}