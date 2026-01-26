package com.ch.stompserver.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//대화 한 건을 담을 DTO
@Getter
@Setter
@AllArgsConstructor
public class ChatMessage {
    private String name;
    private String message;
    private String emoji;
    private LocalDateTime time;

}
