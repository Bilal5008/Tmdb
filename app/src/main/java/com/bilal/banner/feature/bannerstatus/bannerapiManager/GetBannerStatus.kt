package com.bilal.banner.feature.bannerstatus.bannerapiManager

import com.bilal.banner.feature.bannerstatus.bannerinterface.GetBannerStatusInterface
import com.bilal.banner.feature.bannerstatus.viewmodel.BannerViewModel

class GetBannerStatus(private val movieViewModel: BannerViewModel) : GetBannerStatusInterface {
    override fun getMovies(query: String, apiToken: String) {
        movieViewModel.prepareMovieListRepo(query)
    }

}