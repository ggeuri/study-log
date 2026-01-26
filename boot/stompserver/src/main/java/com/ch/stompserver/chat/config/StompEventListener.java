package com.ch.stompserver.chat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.security.Principal;

//클라이언트 접속. 구독요청. 끊기 등 서버측에서 감지하고싶을때
@Slf4j
@Component
public class StompEventListener {

    //접속 감지 메서드
    @EventListener
    public void handleConnect(SessionConnectedEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        //여기서의 세션은 웹소켓 접속 성공했을때 반환되는 세션 (웹소켓세션_접속마다 1:1대응)

        String sessionId = accessor.getSessionId();
        Principal user = accessor.getUser();

        log.debug("접속시 추출한 정보 session={}, user={}", sessionId, user);
    }

    //구독 요청 들어올 때를 감지하기 위한 메서드
    @EventListener
    public void handleSubscribe(SessionSubscribeEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        String sessionId = accessor.getSessionId();
        String dest = accessor.getDestination(); // /topic/room/2
        String subId = accessor.getSubscriptionId();  //  구독 구분값 (ex)sub-0
        Principal user = accessor.getUser();

        log.debug("[구독하자~~~~~]접속시 추출한 정보 session={}, user={}, dest={}, subID={}"
                , sessionId, user,dest,subId);

    }

    //구독 해제
    @EventListener
    public void handleUnsubscribe(SessionUnsubscribeEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        //구독 해제할땐 구독id필요
        String subId = accessor.getSubscriptionId();  //  구독 구분값 (ex)sub-0

        log.debug("구독해제~~~~~~Id는:{}",subId);

    }

    //접속 해제
    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        log.debug("{} 접속해제됨~~~~~~~",sessionId);
    }
}
