package com.bilal.banner.feature.bannerstatus.bannerapiManager

import com.bilal.banner.networking.Filter
import com.bilal.banner.utils.AppConstants

class BannerApiManager {

    fun popularMovies(popularMovies: GetBannerStatus) {
        popularMovies.getMovies(Filter.UPCOMING.filter, AppConstants.API_TOKEN)
    }

}