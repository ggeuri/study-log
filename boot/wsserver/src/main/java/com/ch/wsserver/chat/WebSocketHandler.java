package com.ch.wsserver.chat;

import com.ch.wsserver.chat.dto.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.lang.runtime.ObjectMethods;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    // [JSON 변환기]
    // - 스프링부트에 Jackson(ObjectMapper) 기본 포함
    // - 클라가 보낸 JSON 문자열(payload)을 ChatMessage 객체로 변환할 때 사용
    private final ObjectMapper objectMapper;

    // [연결 성공 시 1회 호출]
    // - session: 이 접속(클라이언트 1명)을 대표하는 객체(세션 id로 구분)
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("{}의 접속감지",session.getId());

        // [서버 → 클라 첫 메시지]
        // - 연결되자마자 "CONNECTED" 안내를 한 번 보내는 용도
        session.sendMessage(new TextMessage("CONNECTED : SESSION_ID = " + session.getId()));
    }

    // [메시지 수신 시마다 호출]
    // - 클라가 send()로 보낸 TextMessage가 여기로 들어옴
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // [payload] 클라가 보낸 실제 문자열(JSON일 수도, 일반문자열일 수도)
        String payload = message.getPayload();
        log.debug("클라이언트전송메시지:{}",payload);

        // [JSON → DTO]
        // - payload가 JSON 형태라면 ChatMessage로 매핑 가능
        ChatMessage chatMessage = objectMapper.readValue(payload,ChatMessage.class);

        // [응답 DTO 생성 예시]
        // - 서버에서 시간(now) 같은 값을 붙여서 "서버 기준 메시지"를 만들 수 있음
        ChatMessage response = new ChatMessage(
                chatMessage.getId(),
                chatMessage.getMessage(),
                chatMessage.getIcon(),
                LocalDateTime.now()
        );

        //전송을 위해서는 이제 다시 문자열화 필요함
        String json = objectMapper.writeValueAsString(response);
        log.debug("클라에게 전송할 문자열{}", json);

        // [에코(Echo)]
        // - 받은 메시지를 그대로 다시 보내는 동작
        // - DTO로 가공해서 보내려면 response를 JSON으로 바꿔서 send해야 함
        session.sendMessage(new TextMessage(json));
    }

    // [전송 중 에러 발생 시 호출]
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    // [연결 종료 시 1회 호출]
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.debug("{}의 접속끊기",session.getId());
    }
}