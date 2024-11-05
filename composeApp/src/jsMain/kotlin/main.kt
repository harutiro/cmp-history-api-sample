import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.compose.rememberNavController
import kotlinx.browser.document
import me.tbsten.sample.cmpHistoryApi.AppNavHost
import me.tbsten.sample.cmpHistoryApi.navigation.bindBrowserUrl
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        val body = document.body ?: return@onWasmReady
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
}
