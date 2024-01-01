package com.srinivas.news.data.repository

import com.srinivas.news.data.models.NewsResponse
import com.srinivas.news.network.NewsApi

class NewsRepositoryImpl(private val newsApi: NewsApi) : NewsRepository {
    override suspend fun fetchNews(): NewsResponse {
        return newsApi.fetchNews()
    }

    override suspend fun popularNews(): NewsResponse {
        return newsApi.fetchPopularNews()
    }
}