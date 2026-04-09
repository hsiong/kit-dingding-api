package com.example.dingding.service;

import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import com.dingtalk.open.app.api.chatbot.BotReplier;
import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 机器人消息回声消费者。
 * 实现 OpenDingTalkCallbackListener 接口，将收到的消息原样返回（Echo 模式）。
 */
@Component
public class BotEchoTextConsumer implements OpenDingTalkCallbackListener<ChatbotMessage, Void> {

   
    /**
     * 执行回调逻辑，将收到消息原样返回（Echo）。
     * 
     * @param message 钉钉推送的消息内容
     * @return 返回 null，因为此回调不需要返回特定的数据结构
     */
    @Override
    public Void execute(ChatbotMessage message) {
        String response = String.format("echo received message: [%s]", 
            message.getText().getContent().trim());
        try {
            BotReplier.fromWebhook(message.getSessionWebhook()).replyText(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
