package com.digi.tmdb.feature.movielist

import com.digi.tmdb.feature.movielist.list_api_interface.IUpcomingMovies
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel

class UpCommingMovies(private val movieViewModel: MovieListViewModel) : IUpcomingMovies {

    override fun getUpComingMovies(filter: String, apiToken: String) {
        movieViewModel.prepareUpCommingMovieRepo(filter)
    }


}