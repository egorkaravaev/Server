package com.server.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
//@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer
        //WebSocketMessageBrokerConfigurer
{
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler(), "/server").setAllowedOrigins("*");
    }

    @Bean
    public ServerHandler handler(){
        return new ServerHandler();
    }

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
//        registry.addEndpoint("/server")
//                .setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
//                .setAllowedOrigins("*");
//    }
}
