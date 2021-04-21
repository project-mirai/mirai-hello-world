package org.example.mirai;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.BotConfiguration;

public class JavaMain {
    public static void main(String[] args) {
        Bot bot = BotFactory.INSTANCE.newBot(123456, "test");
        bot.login();

        JavaMain.afterLogin(bot);
    }

    public static void afterLogin(Bot bot) {
        long yourQQNumber = 123456789;
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {
            if (event.getSender().getId() == yourQQNumber) {
                event.getSubject().sendMessage(new MessageChainBuilder()
                        .append(new QuoteReply(event.getMessage()))
                        .append("Hi, you just said: '")
                        .append(event.getMessage())
                        .append("'")
                        .build()
                );
            }
        });
    }
}

class WithConfiguration1 {
    public static void main(String[] args) {
        // 使用自定义配置
        Bot bot = BotFactory.INSTANCE.newBot(123456, "test", new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.ANDROID_PAD); // 切换协议
        }});
        bot.login();

        JavaMain.afterLogin(bot);
    }
}
