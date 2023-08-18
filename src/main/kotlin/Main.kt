import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import ui.main.MainScreen
import ui.theme.*
import ui.welcome.WelcomeScreen
import utils.SystemUtil
import kotlin.system.exitProcess

@Composable
@Preview
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
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

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true
    ) {
        DesktopMenuTheme() {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // 设置窗口拖动区域
                WindowDraggableArea {
                    TitleBar()
                }
                App()
            }
        }
    }
}

/**
 * 自定义标题栏
 */
@Composable
fun TitleBar() {
    val isDark = isSystemInDarkTheme()
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(if (isDark) md_theme_dark_background else md_theme_light_background)
            .padding(10.dp)
    ) {
        Text(
            text = "桌面菜单",
            modifier = Modifier.padding(start = 10.dp),
            color = if (isDark) Color.White else Color.Black
        )

        Row (
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = {
                    exitProcess(0)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}
