package com.ch.stompserver.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //클라가 접속할 주소(엔드포인트) /ws/stomp
        registry.addEndpoint("/ws/stomp")
                .setAllowedOrigins("http://localhost:7777")
                .withSockJS(); //SockJS는 WebSocket이 실행될 수 없는 환경 시 대안으로 SockJs를 사용할 수 있음(효과동일)
    }

//    Stomp 이용시 개발자가 멀티캐스팅 채팅 구현 손쉽게 개발 가능

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //서버가 클라 메시지 받을때 접두어 = 클라가 서버로 보내는 요청에 붙일 prefix (클 -> 서)
        registry.setApplicationDestinationPrefixes("/app");
        //(클라가 구독요청시) 붙을 접두어 = 서버가 클라에게 브로드캐스트할때 붙일 prefix (서 -> 클)
        registry.enableSimpleBroker("/topic"); // /topic/health, /topic/food, /topic/game 같은거


    }
}
