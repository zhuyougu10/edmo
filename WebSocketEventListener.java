package lzpu.logistics.hq.websocket;

import lombok.extern.log4j.Log4j2;
import lzpu.logistics.hq.utils.MsgUtil;
import lzpu.logistics.hq.websocket.vo.Message;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.Date;

@Component
@Log4j2
public class WebSocketEventListener {


    @EventListener
    public void handleSessionConnectedEvent(SessionConnectedEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal principal = accessor.getUser();

        if (principal != null) {
            String userId = principal.getName();
            // 发送私信
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            MsgUtil.sendPrivateNotify(userId, new Message("system", userId, "连接成功", new Date()));
                        }
                    },
                    200
            );
        }

    }

    @EventListener
    public void handleDisconnectEvent(SessionDisconnectEvent event) {
        // 获取断开连接的会话信息
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        String username = accessor.getUser().getName();
        log.info("用户已断开连接: {}, Session ID: {}", username, sessionId);
    }

}
