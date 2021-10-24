package com.bilal.banner.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bilal.banner.feature.bannerstatus.viewmodel.BannerViewModel

class GlobalViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            BannerViewModel::class.java -> {
                BannerViewModel() as T
            }

            else -> {
                super.create(modelClass)
            }
        }
    }


}