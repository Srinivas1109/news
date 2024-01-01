package com.srinivas.news.data.repository

import com.srinivas.news.data.models.NewsResponse

interface NewsRepository {
    suspend fun fetchNews() : NewsResponse
    suspend fun popularNews() : NewsResponse
}