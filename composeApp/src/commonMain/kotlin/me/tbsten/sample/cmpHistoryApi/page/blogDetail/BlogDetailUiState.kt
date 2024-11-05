package me.tbsten.sample.cmpHistoryApi.page.blogDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import me.tbsten.sample.cmpHistoryApi.Blog
import me.tbsten.sample.cmpHistoryApi.fetchBlogById
import me.tbsten.sample.cmpHistoryApi.fetchNextBlogId
import me.tbsten.sample.cmpHistoryApi.fetchPrevBlogId

sealed interface BlogDetailUiState {
    data object Loading : BlogDetailUiState

    class Success(
        val blog: Blog,
        val prevBlogId: String?,
        val nextBlogId: String?,
    ) : BlogDetailUiState

    data object NotFound : BlogDetailUiState
}

@Composable
fun rememberBlogDetailUiState(blogId: String?): BlogDetailUiState {
    var uiState by remember { mutableStateOf<BlogDetailUiState>(BlogDetailUiState.Loading) }

    LaunchedEffect(blogId) {
        val (blog, prevId, nextId) = blogId?.let {
            val blogDeferred = async { fetchBlogById(it) }
            val prevIdDeferred = async { fetchPrevBlogId(it) }
            val nextIdDeferred = async { fetchNextBlogId(it) }
            awaitAll(blogDeferred, prevIdDeferred, nextIdDeferred)
        } ?: return@LaunchedEffect
        uiState = if (blog == null) {
            BlogDetailUiState.NotFound
        } else {
            BlogDetailUiState.Success(
                blog = blog as Blog,
                prevBlogId = prevId as String?,
                nextBlogId = nextId as String?,
            )
        }
    }
    return uiState
}
