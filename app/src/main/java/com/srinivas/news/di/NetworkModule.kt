package com.srinivas.news.di

import com.srinivas.news.data.Constants.BASE_URL
import com.srinivas.news.network.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        val retrofit by lazy {
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val service by lazy { retrofit.create(NewsApi::class.java) }
        return service
    }

}