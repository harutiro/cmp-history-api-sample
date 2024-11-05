import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.compose.rememberNavController
import kotlinx.browser.document
import me.tbsten.sample.cmpHistoryApi.AppNavHost
import me.tbsten.sample.cmpHistoryApi.navigation.bindBrowserUrl

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val body = document.body ?: return
    ComposeViewport(body) {
        val navController = rememberNavController().apply {
            bindBrowserUrl()
        }
        val startDestination = document.location?.pathname ?: "/"
        AppNavHost(
            startDestination = startDestination,
            navController = navController,
        )
    }
}
