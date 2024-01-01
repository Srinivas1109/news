package com.srinivas.news.data.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.srinivas.news.workers.PopularNewsWorker
import java.time.Duration

class PopularNewsRepositoryImpl(context: Context) : PopularNewsRepository {

    private val workManager = WorkManager.getInstance(context)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun fetchPopularNews() {
        val newsWorker =
            PeriodicWorkRequestBuilder<PopularNewsWorker>(repeatInterval = Duration.ofMinutes(15L)).build()
        workManager.enqueueUniquePeriodicWork(
            "PopularNews",
            ExistingPeriodicWorkPolicy.KEEP,
            newsWorker
        )

        workManager.enqueueUniquePeriodicWork(
            "Popular Periodic news",
            ExistingPeriodicWorkPolicy.KEEP,
            newsWorker
        )

//        val newsWorker = OneTimeWorkRequestBuilder<PopularNewsWorker>()
//
//        workManager.enqueueUniqueWork("popular news", ExistingWorkPolicy.REPLACE, newsWorker.build())
    }
}