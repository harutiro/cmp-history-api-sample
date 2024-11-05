package me.tbsten.sample.cmpHistoryApi.page.top

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.tbsten.sample.cmpHistoryApi.Blog
import me.tbsten.sample.cmpHistoryApi.fetchBlogs

@Composable
fun TopPage(
    navigateBlogDetail: (Blog) -> Unit,
) {
    var blogs by remember { mutableStateOf<List<Blog>?>(null) }
    LaunchedEffect(Unit) {
        blogs = fetchBlogs()
    }

    Scaffold { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Text(
                text = "TopPage",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            blogs.let { blogs ->
                if (blogs != null) {
                    LazyColumn {
                        items(blogs) { blog ->
                            Row(
                                Modifier
                                    .clickable { navigateBlogDetail(blog) }
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(blog.title)
                            }
                        }
                    }
                } else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }

        }
    }
}
