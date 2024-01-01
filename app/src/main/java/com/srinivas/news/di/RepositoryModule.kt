package com.srinivas.news.di

import android.content.Context
import com.srinivas.news.data.repository.NewsRepository
import com.srinivas.news.data.repository.NewsRepositoryImpl
import com.srinivas.news.data.repository.PopularNewsRepository
import com.srinivas.news.data.repository.PopularNewsRepositoryImpl
import com.srinivas.news.network.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi)
    }

    @Provides
    @Singleton
    fun providesApplicationPopularNewsRepository(@ApplicationContext appContext: Context): PopularNewsRepository {
        return PopularNewsRepositoryImpl(context = appContext)
    }
}