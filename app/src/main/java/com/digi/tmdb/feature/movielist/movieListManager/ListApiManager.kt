package com.digi.tmdb.feature.movielist.movieListManager


import com.digi.tmdb.base.Filter
import com.digi.tmdb.utils.AppConstants

class ListApiManager {

  fun popularMovies(popularMovies: PopularMovies) {
    popularMovies.getMovies(Filter.UPCOMING.filter, AppConstants.API_TOKEN)
  }

}