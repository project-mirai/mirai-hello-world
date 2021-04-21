package org.example.mirai

import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.utils.BotConfiguration.MiraiProtocol.ANDROID_PAD

object WithoutConfiguration {
    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
        val bot = BotFactory.newBot(123456, "").alsoLogin()

        bot.getFriend(123456789)?.sendMessage("Hello, World!")
        bot.eventChannel.subscribeAlways<FriendMessageEvent> {
            if (sender.id == 123456L) {
                subject.sendMessage(message.quote() + "Hi, you just said '${message.content}'")
            }
        }
    }
}

object WithConfiguration {
    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
        // 使用自定义配置
        val bot = BotFactory.newBot(123456, "password") {
            fileBasedDeviceInfo() // 使用 device.json 存储设备信息
            protocol = ANDROID_PAD // 切换协议
        }.alsoLogin()

        bot.getFriend(123456789)?.sendMessage("Hello, World!")
        bot.eventChannel.subscribeAlways<FriendMessageEvent> {
            if (sender.id == 123456L) {
                subject.sendMessage(message.quote() + "Hi, you just said '${message.content}'")
            }
        }
    }
}