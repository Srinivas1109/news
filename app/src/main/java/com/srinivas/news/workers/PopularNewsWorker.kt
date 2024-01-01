package com.srinivas.news.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.srinivas.news.data.repository.NewsRepository
import com.srinivas.news.utils.sendNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PopularNewsWorker @AssistedInject constructor(
    private val newsRepository: NewsRepository,
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val newsResponse = newsRepository.popularNews()
            val popularArticles = newsResponse.articles
            println("Worker Executing: ${popularArticles.size}")
            val popularArticleIndex = (popularArticles.indices).random()
            val popularArticle = newsResponse.articles[popularArticleIndex]
            sendNotification(applicationContext, popularArticle.title, popularArticle.description)
            Result.success()
        } catch (throwable: Throwable) {
            println("Worker Executing: Failed $throwable")
            throwable.printStackTrace()
            Result.failure()
        }
    }
}