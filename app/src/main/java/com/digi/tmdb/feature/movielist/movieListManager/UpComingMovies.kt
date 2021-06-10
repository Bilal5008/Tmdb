package com.digi.tmdb.feature.movielist.movieListManager

import com.digi.tmdb.feature.movielist.list_api_interface.IUpcomingMovies
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel

class UpComingMovies(private val movieViewModel: MovieListViewModel) : IUpcomingMovies {

    override fun getUpComingMovies(filter: String, apiToken: String) {
        movieViewModel.prepareUpCommingMovieRepo(filter)
    }


}