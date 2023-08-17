package ui.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 欢迎页面
 * 一般在用户第一次打开程序时展示
 * @param onEnterButtonClick 进入程序按钮点击事件
 */
@Composable
fun WelcomeScreen(onEnterButtonClick: () -> Unit) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelComeText()
        EnterButton { onEnterButtonClick() }
    }
}

/**
 * 欢迎文本
 */
@Composable
fun WelComeText() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "DesktopMenu",
            fontSize = 58.sp,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "桌面菜单",
            fontSize = 36.sp,
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "欢迎使用",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center
        )
    }
}

/**
 * 进入程序按钮
 * @param onEnterButtonClick 进入程序按钮点击事件
 */
@Composable
fun EnterButton(onEnterButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(top = 40.dp)
    ) {
        Button(onClick = {
            onEnterButtonClick()
        }) {
            Text(
                text = "进入程序"
            )
        }
    }
}