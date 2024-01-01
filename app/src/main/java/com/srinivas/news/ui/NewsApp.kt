package com.srinivas.news.ui

import androidx.compose.runtime.Composable
import com.srinivas.news.ui.screens.ErrorScreen
import com.srinivas.news.ui.screens.LoadingScreen
import com.srinivas.news.ui.screens.NewsArticlesScreen
import com.srinivas.news.ui.viewmodels.MainUiState

@Composable
fun NewsApp(
    uiState: MainUiState
) {
    when (uiState) {
        is MainUiState.Loading -> LoadingScreen()
        is MainUiState.Error -> ErrorScreen(errorMessage = uiState.message)
        is MainUiState.Success -> NewsArticlesScreen(articles = uiState.newsArticles)
    }
}