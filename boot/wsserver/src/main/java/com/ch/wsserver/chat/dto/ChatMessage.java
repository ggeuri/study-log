package com.ch.wsserver.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//메시지 한건을 표현한 DTO객체
//메시지는 문자열임에도 불구하고 DTO로 정의하는 이유 - java객체와 json문자열 자동변환해주는 MessageConverter쓰려고
@Getter
@Setter
@AllArgsConstructor
public class ChatMessage {
    private String id;
    private String message;
    private String icon;
    private LocalDateTime time;


}
