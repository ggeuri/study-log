package com.ch.stompserver.chat.controller;

import com.ch.stompserver.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate template;

    //클라의 요청 받기. 클라이언트가 서버에게 요청시 Config에서 "/app"해놨으니까 여기서 붙이면됨. /app/room/{roomId}/send
    @MessageMapping("/room/{roomId}/send")
    public void send(@DestinationVariable String roomId, ChatMessage chatMessage){
        log.debug("에? 클라가 보낸 메시지 {}랑 룸아이디{}", chatMessage.getMessage(),roomId);

        //다시 클라에게 메시지 보내기. 구독주소 같은 클라에게
        //클라이언트에게 보내기전에 서버측에서 메시지 생성시간 처리하여 보내자
        chatMessage.setTime(LocalDateTime.now());
        template.convertAndSend("/topic/room/" + roomId, chatMessage); //변수1 주소, 2는 메시지

    }


}
