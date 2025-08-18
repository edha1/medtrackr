package com.medtrackr.medtrackr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // enales websocket message handling with a message broker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Route message based on destination prefixes 
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // for messages from server to client 
        config.setApplicationDestinationPrefixes("/app"); /// for messages from client to server 
    }

    /**
     * Register the endpoint for websocket connections
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}
