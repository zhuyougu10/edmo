package lzpu.logistics.hq.websocket.handler;

import lzpu.logistics.common.core.domain.model.LoginUser;
import lzpu.logistics.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    private static TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        CustomHandshakeHandler.tokenService = tokenService;
    }



    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        LoginUser loginUser = tokenService.getLoginUser(servletRequest.getServletRequest());
        String userId;
        if (loginUser != null) {
            userId = loginUser.getUserId().toString();
            attributes.put("userId", userId);
        } else {
            userId = "";
        }
        return () -> userId;
    }

}
