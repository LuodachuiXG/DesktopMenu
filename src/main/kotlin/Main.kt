import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.main.MainScreen
import ui.theme.DesktopMenuTheme

@Composable
@Preview
fun App() {
    DesktopMenuTheme {
        MainScreen()
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
