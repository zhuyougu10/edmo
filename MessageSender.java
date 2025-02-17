package lzpu.logistics.hq.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageSender(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 发送广播消息
     *
     * @param destination 发送到的目标地址
     * @param message     消息内容
     */
    public void sendBroadcastMessage(String destination, Object message) {
        messagingTemplate.convertAndSend(destination, message);
    }

    /**
     * 发送点对点消息
     *
     * @param userId      接收消息的用户ID
     * @param destination 发送到的目标地址
     * @param message     消息内容
     */
    public void sendPrivateMessage(String userId, String destination, Object message) {
        messagingTemplate.convertAndSendToUser(userId, destination, message);
    }
}
