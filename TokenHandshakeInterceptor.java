package lzpu.logistics.hq.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import lzpu.logistics.common.core.domain.model.LoginUser;
import lzpu.logistics.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@Component
public class TokenHandshakeInterceptor implements HandshakeInterceptor {

    private static TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        TokenHandshakeInterceptor.tokenService = tokenService;
    }




    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws IllegalAccessException {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            LoginUser loginUser = tokenService.getLoginUser(servletRequest.getServletRequest());
                String userId = loginUser.getUserId().toString();
                attributes.put("userId", userId);
                return true;

        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
        // 可选的后续处理
    }


}
