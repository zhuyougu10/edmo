package lzpu.logistics.hq.utils;

import lzpu.logistics.hq.websocket.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgUtil {

    private static MessageSender messageSender;

    @Autowired
    private void setMessageSender(MessageSender messageSender) {
        MsgUtil.messageSender = messageSender;
    }

    public static void sendBroadcastMessage(String destination, Object message) {
        messageSender.sendBroadcastMessage(destination, message);
    }

    public static void sendPrivateNotify(String userId, Object message) {
        messageSender.sendPrivateMessage(userId, "/queue/notify", message);
    }
}
