package com.ch.model1.dto;

import lombok.Data;

// 응답 바디: items + 페이징 정보
@Data
public class Body {
    private Items items;
    private int numOfRows;
    private int pageNo;
    private int totalCount;
}