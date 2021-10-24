package com.bilal.banner.feature.bannerstatus.bannerinterface

interface GetBannerStatusInterface {
    fun getMovies(query: String, apiToken: String)
}