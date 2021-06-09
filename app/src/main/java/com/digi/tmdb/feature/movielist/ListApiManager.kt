package com.digi.tmdb.feature.movielist

import com.digi.tmdb.retrofit.Filter
import com.digi.tmdb.utils.AppConstants

class ListApiManager {

    fun popularMovies(popularMovies: PopularMovies) {
        popularMovies.getMovies(Filter.POPULAR.filter, AppConstants.API_TOKEN)
    }

    fun upCommingMovies(upCommingMovies: UpCommingMovies) {
        upCommingMovies.getUpComingMovies(Filter.POPULAR.filter, AppConstants.API_TOKEN)

    }

    fun nowPlayingMovies(nowPlayingMovies: NowPlayingMovies) {

        nowPlayingMovies.getNowPlayingMovies(Filter.NOW_PLAYING.filter, AppConstants.API_TOKEN)
    }
}