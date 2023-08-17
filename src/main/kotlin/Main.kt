import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.main.MainScreen
import ui.theme.DesktopMenuTheme
import ui.welcome.WelcomeScreen
import utils.SystemUtil

@Composable
@Preview
fun App() {
    DesktopMenuTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            // 通过判断程序的配置文件存储目录是否存在来决定是否展示欢迎页
            val configDir = SystemUtil.getConfigDir()
            val configDirIsExists = remember { mutableStateOf(configDir.exists()) }
            if (configDirIsExists.value) {
                MainScreen()
            } else {
                WelcomeScreen {
                    // 欢迎页上的进入程序按钮被点击事件
                    // 创建配置文件目录
                    configDir.mkdirs()
                    configDirIsExists.value = true
                }
            }

        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DesktopMenu"
    ) {
        App()
    }
}
