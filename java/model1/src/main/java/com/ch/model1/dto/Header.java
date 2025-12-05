package com.ch.model1.dto;

import lombok.Data;

// 응답 헤더 정보(resultCode, resultMsg 등)
@Data
public class Header {
    private String resultCode;
    private String resultMsg;
}