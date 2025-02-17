package lzpu.logistics.hq.websocket.config;

import lzpu.logistics.hq.websocket.handler.CustomHandshakeHandler;
import lzpu.logistics.hq.websocket.interceptor.TokenHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 代理前缀，用于广播与点对点消息
        config.enableSimpleBroker("/topic", "/queue");
        // 使用标准 "/user" 作为用户消息前缀
        config.setUserDestinationPrefix("/user");
        // 定义应用程序发送消息的前缀
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .addInterceptors(new TokenHandshakeInterceptor())
                .setHandshakeHandler(new CustomHandshakeHandler())
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

}
