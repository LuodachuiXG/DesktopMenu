package utils

import java.io.File

/**
 * 系统操作有关的类
 */
class SystemUtil {
    companion object {
        /**
         * 获取当前系统类型
         */
        fun getCurrentOS(): OS {
            val osName = System.getProperty("os.name").uppercase()
            return when {
                osName.contains("WINDOWS") -> OS.WINDOWS
                osName.contains("LINUX") -> OS.LINUX
                osName.contains("MAC") -> OS.MACOS
                else -> OS.OTHER
            }
        }

        /**
         * 根据不同操作系统环境，返回程序配置文件应存储的文件夹
         */
        fun getConfigDir(): File {
            val userHome = System.getProperty("user.home")
            val dir = when (getCurrentOS()) {
                OS.WINDOWS -> "\\AppData\\Local\\DesktopMenu"
                else -> "/.local/share/JcNetwork"
            }
            return File(userHome + dir)
        }
    }
}

/**
 * 表明系统的枚举类
 */
enum class OS {
    WINDOWS,
    LINUX,
    MACOS,
    OTHER
}