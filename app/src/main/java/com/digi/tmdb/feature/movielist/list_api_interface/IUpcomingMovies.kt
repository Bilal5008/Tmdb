package com.digi.tmdb.api_interface

interface IUpcomingMovies {
    fun getUpComingMovies(filter: String, apiToken: String)
}