package com.digi.tmdb.feature.movielist.movieListManager

import com.digi.tmdb.feature.movielist.list_api_interface.IPopularMovies
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel

class PopularMovies(private val movieViewModel: MovieListViewModel) : IPopularMovies {
  override fun getMovies(query: String, apiToken: String) {
    movieViewModel.prepareMovieListRepo(query)
  }


}