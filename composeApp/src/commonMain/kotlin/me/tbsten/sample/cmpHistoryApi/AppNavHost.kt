package me.tbsten.sample.cmpHistoryApi

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.tbsten.sample.cmpHistoryApi.page.blogDetail.BlogDetailPage
import me.tbsten.sample.cmpHistoryApi.page.top.TopPage

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "/",
    ) {
        composable("/") {
            TopPage(
                navigateBlogDetail = {
                    navController.navigate("/blog/${it.id}")
                },
            )
        }
        composable("/blog/{blogId}") {
            val blogId = it.arguments?.getString("blogId")
            BlogDetailPage(
                blogId = blogId,
                navigateBlogDetail = { blogId ->
                    navController.navigate("/blog/$blogId")
                },
            )
        }
    }
}
