package com.srinivas.news.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srinivas.news.data.models.Article
import com.srinivas.news.data.repository.NewsRepository
import com.srinivas.news.data.repository.PopularNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MainUiState {
    data object Loading : MainUiState
    data class Error(val message: String) : MainUiState
    data class Success(val newsArticles: List<Article>) : MainUiState
}

enum class Status(val code: String) {
    Ok("ok"), Error("error")
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    popularNewsRepository: PopularNewsRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        fetchNews()
        popularNewsRepository.fetchPopularNews()
    }

    private fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                MainUiState.Loading
            }
            try {
                val newsResponse = newsRepository.fetchNews()
                when (newsResponse.status) {
                    Status.Ok.code -> _uiState.update {
                        MainUiState.Success(newsArticles = newsResponse.articles)
                    }

                    Status.Error.code -> _uiState.update {
                        val message =
                            "Something went wrong try again later!"
                        MainUiState.Error(message)
                    }
                }
            } catch (e: Exception) {
                MainUiState.Error("Something went wrong try again later!")
                e.printStackTrace()
            }
        }
    }


}