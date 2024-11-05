package me.tbsten.sample.cmpHistoryApi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.browser.document
import kotlinx.browser.window

@Composable
fun NavController.bindBrowserUrl(
    basePath: String = "",
    navBackStackEntryToBrowserUrl: (NavBackStackEntry) -> String? = { it.route },
) {
    val currentBackStack by currentBackStack.collectAsState()
    val currentBackStackEntry by currentBackStackEntryAsState()
    val browserUrl by remember {
        derivedStateOf {
            currentBackStackEntry?.let(navBackStackEntryToBrowserUrl)
        }
    }
    LaunchedEffect(browserUrl) {
        window.history.pushState(null, "", "$basePath$browserUrl")
    }
    LaunchedEffect(Unit) {
        window.addEventListener("popstate", {
            val targetRoute = currentBackStack.firstOrNull {
                it.route == document.location?.pathname
            }?.route ?: return@addEventListener
            popBackStack(targetRoute, inclusive = false)
        })
    }
}

private val NavBackStackEntry.route: String?
    get() {
        val destinationRoute = this.destination.route ?: return null
        val arguments = this.arguments ?: return destinationRoute
        val argKeys = arguments.keySet()
        return argKeys.toList().fold(destinationRoute) { route, argKey ->
            @Suppress("ReplaceGetOrSet", "DEPRECATION")
            val argValue = arguments.get(argKey)
            route.replace("{$argKey}", argValue.toString())
        }
    }
