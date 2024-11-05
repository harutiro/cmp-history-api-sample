package me.tbsten.sample.cmpHistoryApi

import kotlinx.coroutines.delay

data class Blog(
    val id: String,
    val title: String,
    val details: String,
)

private val fakeBlogData = List(20) {
    Blog("$it", "my blog $it", "blog ".repeat(500))
}

suspend fun fetchBlogs(): List<Blog> {
    delay(1_000)
    return fakeBlogData
}

suspend fun fetchBlogById(id: String): Blog? {
    delay(1_000)
    return fakeBlogData.firstOrNull { it.id == id }
}

suspend fun fetchPrevBlogId(id: String): String? {
    delay(250)
    return fakeBlogData.getOrNull(fakeBlogData.indexOfFirst { it.id == id } - 1)?.id
}

suspend fun fetchNextBlogId(id: String): String? {
    delay(250)
    return fakeBlogData.getOrNull(fakeBlogData.indexOfFirst { it.id == id } + 1)?.id
}
