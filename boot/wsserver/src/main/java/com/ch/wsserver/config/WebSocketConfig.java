package com.ch.wsserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    //웹소캣 핸들러객체 보유(접속해제메시지)
    private final WebSocketHandler webSocketHandler;

    //개발단계에서는 React, Vue와 같은 프로젝트에서 접근. 접근자체를 허용해줘야함. 하지만 제품화시켰을 경우 존재하는 도메인 열어주기 ex)test.com


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler,"/ws/echo")
                .setAllowedOriginPatterns("*") ; // 개발할때는 모두 열어주고 실제제품화할때 특정도메인으로

    }
}
