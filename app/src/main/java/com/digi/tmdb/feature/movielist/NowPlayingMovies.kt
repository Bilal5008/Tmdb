package com.digi.tmdb.feature.movielist

import com.digi.tmdb.feature.movielist.list_api_interface.INowPlayingMovies
import com.digi.tmdb.feature.movielist.viewmodel.MovieListViewModel

class NowPlayingMovies(private val movieViewModel: MovieListViewModel) : INowPlayingMovies {
    override fun getNowPlayingMovies(filter: String, apiToken: String) {
        movieViewModel.prepareNowPlayingMovieRepo(filter)
    }


}