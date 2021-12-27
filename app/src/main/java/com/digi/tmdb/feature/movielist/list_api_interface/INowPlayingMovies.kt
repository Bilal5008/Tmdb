package com.digi.tmdb.feature.movielist.list_api_interface

interface INowPlayingMovies {
  fun getNowPlayingMovies(filter: String, apiToken: String)
}