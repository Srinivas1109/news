package com.srinivas.news.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.srinivas.news.data.models.Article

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsArticlesScreen(
    modifier: Modifier = Modifier, articles: List<Article>
) {
    val state = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = state,
        verticalArrangement = Arrangement.Center,
        flingBehavior = flingBehavior
    ) {
        items(items = articles, key = {
            it.urlToImage
        }) {
            NewsItem(article = it)
        }
    }
}

@Composable
fun NewsItem(
    modifier: Modifier = Modifier, article: Article
) {
    val uriHandler = LocalUriHandler.current
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            append("More info >>")
        }
    }
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            SubcomposeAsyncImage(
                model = article.urlToImage,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxWidth()
            )
            Text(
                text = article.source.name,
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 8.dp, top = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(4.dp),
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = article.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = "-${article.author}",
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(
            text = article.description.trim(),
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Start,
            fontSize = 14.sp
        )
        ClickableText(
            text = annotatedString, onClick = {
                uriHandler.openUri(article.url)
            }, modifier = modifier.padding(8.dp)
        )
    }
}