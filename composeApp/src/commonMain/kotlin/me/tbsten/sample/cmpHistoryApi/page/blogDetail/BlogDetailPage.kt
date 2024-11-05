package me.tbsten.sample.cmpHistoryApi.page.blogDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.tbsten.sample.cmpHistoryApi.Blog

@Composable
fun BlogDetailPage(
    blogId: String?,
    navigateBlogDetail: (blogId: String) -> Unit,
) {
    val uiState = rememberBlogDetailUiState(blogId)

    Scaffold(
        topBar = {
            when (uiState) {
                is BlogDetailUiState.Success -> {
                    BlogDetailTopBar(
                        navigatePrev = uiState.prevBlogId?.let { { navigateBlogDetail(it) } },
                        navigateNext = uiState.nextBlogId?.let { { navigateBlogDetail(it) } },
                    )
                }

                else -> {}
            }
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding)) {
            when (uiState) {
                BlogDetailUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                BlogDetailUiState.NotFound ->
                    NotFoundPage()

                is BlogDetailUiState.Success -> {
                    BlogDetailPage(
                        blog = uiState.blog,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlogDetailTopBar(
    navigatePrev: (() -> Unit)?,
    navigateNext: (() -> Unit)?,
) {
    TopAppBar(
        navigationIcon = {
            navigatePrev?.let {
                IconButton(onClick = navigatePrev) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "prev")
                }
            }
        },
        actions = {
            navigateNext?.let {
                IconButton(onClick = navigateNext) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, "next")
                }
            }
        },
        title = {},
    )
}

@Composable
private fun BlogDetailPage(
    blog: Blog,
) {
    Column {
        Text(
            text = blog.title,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(16.dp),
        )
        Text(
            text = blog.details,
        )
    }
}

@Composable
private fun NotFoundPage() {
    Scaffold { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Text("Not Found Page")
        }
    }
}
