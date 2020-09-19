package org.example.mirai

import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.newBot
import net.mamoe.mirai.utils.BotConfiguration.MiraiProtocol.ANDROID_PAD

suspend fun main() {
    val bot = BotFactory.newBot(123456, "").alsoLogin()

    bot.getFriend(123456789)?.sendMessage("Hello, World!")
}

object WithConfiguration {
    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
        // 使用自定义配置
        val bot = BotFactory.newBot(123456, "") {
            fileBasedDeviceInfo() // 使用 device.json 存储设备信息
            protocol = ANDROID_PAD // 切换协议
        }.alsoLogin()

        bot.getFriend(123456789)?.sendMessage("Hello, World!")
    }
}