package utils

import org.ini4j.Wini
import java.io.File

/**
 * 配置文件类
 * 用于存储一些键值对的程序数据
 * 使用 .ini 方式
 */
class ConfigUtil private constructor() {
    companion object {
        // 当前类实例
        private var _instance: ConfigUtil? = null
        // Wini 实例，用于操作 ini
        private var _wini: Wini? = null

        /**
         * 获取 ConfigUtil 实例
         */
        fun getInstance(): ConfigUtil {
            if (_instance == null) {
                _instance = ConfigUtil()
                // 获取程序配置文件存放目录
                val configDir = SystemUtil.getConfigDir()
                if (!configDir.exists()) {
                    configDir.mkdirs()
                }
                // 配置文件对象
                val configFile = File(configDir.path + File.separator + "config.ini")
                if (!configFile.exists()) {
                    configFile.createNewFile()
                }
                // 初始化 ini 操作类
                _wini = Wini(configFile)
            }
            return _instance as ConfigUtil
        }
    }

}