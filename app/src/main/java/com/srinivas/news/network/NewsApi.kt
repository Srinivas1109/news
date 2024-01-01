package com.srinivas.news.network

import com.srinivas.news.BuildConfig
import com.srinivas.news.data.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {

    @Headers("Authorization: ${BuildConfig.NEWS_API_KEY}")
    @GET("/v2/everything?q=technology")
    suspend fun fetchNews() : NewsResponse

    @Headers("Authorization: ${BuildConfig.NEWS_API_KEY}")
    @GET("/v2/everything?q=technology&sortBy=popularity")
    suspend fun fetchPopularNews() : NewsResponse
}