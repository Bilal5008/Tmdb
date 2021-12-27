package com.digi.tmdb.feature.movielist.list_api_interface

interface IUpcomingMovies {
  fun getUpComingMovies(filter: String, apiToken: String)
}