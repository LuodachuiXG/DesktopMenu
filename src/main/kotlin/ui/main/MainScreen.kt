package ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // BottomBar 当前选择项
        var selectedItem by remember { mutableStateOf(0) }
        // TopBar 下拉菜单打开状态
        var dropDownMenuOpen by remember { mutableStateOf(false) }
        // Scaffold 状态，可以用来获取侧边栏打开状态
        val scaffoldState = rememberScaffoldState()
        // 获取组合感知作用域，以便在可组合项外启动协程
        val scope = rememberCoroutineScope()

        // 设置 BottomBar 项
        val items = listOf(
            BottomBarItem("首页", Icons.Outlined.Home),
            BottomBarItem("消息", Icons.Outlined.Email),
            BottomBarItem("我", Icons.Outlined.Face)
        )

        // 设置 TopBar 下拉菜单选项
        val menus = listOf("刷新数据", "设置", "退出")

        // 脚手架
        Scaffold (
            topBar = {
                TopAppBar (
                    title = {
                        Text("首页")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scaffoldState.drawerState.apply {
                                scope.launch {
                                    if (isOpen) close() else open()
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                    // 设置 TopBar 右侧按钮
                    actions = {
                        IconButton(onClick = {
                            dropDownMenuOpen = !dropDownMenuOpen
                        }) {
                            Icon(imageVector = Icons.Filled.Face, contentDescription = null)
                        }
                    }
                )

            },
            bottomBar = {
                BottomNavigation {
                    items.forEachIndexed { index, bottomBarItem ->
                        BottomNavigationItem(
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                            },
                            icon = {
                                Icon(imageVector = bottomBarItem.icon, null)
                            },
                            label = {
                                Text(bottomBarItem.name)
                            }
                        )
                    }
                }
            },
            // 侧边栏
            drawerContent = {
                Text(text = "Hello")
            },
            scaffoldState = scaffoldState
        ) {
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    // 此处设置 padding 以便偏移顶部栏和底部栏（如果存在的话），否则报错
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                when (selectedItem) {
                    0 -> {
                        Text("首页")
                    }
                    1 -> {
                        Text("消息")
                    }
                    2 -> {
                        Text("我")
                    }
                }
            }
        }
    }
}

data class BottomBarItem (val name: String, val icon: ImageVector)