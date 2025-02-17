package lzpu.logistics.hq.websocket;

import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

@Service
public class PresenceService {

    private final SimpUserRegistry userRegistry;

    public PresenceService(SimpUserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    /**
     * 判断某个用户是否在线
     * @param username 用户名（或用户标识）
     * @return true: 在线，false: 离线
     */
    public boolean isUserOnline(String username) {
        // userRegistry.getUser(username) 不为 null 且拥有活跃会话时表示在线
        return userRegistry.getUser(username) != null
               && !userRegistry.getUser(username).getSessions().isEmpty();
    }
}
