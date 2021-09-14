package com.digi.tmdb.base.di.module

import com.digi.tmdb.base.ApiHelper
import com.digi.tmdb.base.ApiHelperImpl
import com.digi.tmdb.base.ApiService
import com.digi.tmdb.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    fun provideBaseUrl() = AppConstants.baseURL

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder().build()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =


        Retrofit.Builder()
            .baseUrl(AppConstants.baseURL)
            .client(OkHttpClient.Builder().build())

            .addConverterFactory(GsonConverterFactory.create())

            .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}