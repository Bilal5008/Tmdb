package com.bilal.banner.feature.bannerstatus.repo

import com.bilal.banner.networking.RetroInstance
import com.bilal.banner.utils.AppConstants

class BannerRepository() {
    suspend fun getMovieListObserverRx(query: String) =
        RetroInstance.apiService.getMovies(query, AppConstants.API_TOKEN)
}
