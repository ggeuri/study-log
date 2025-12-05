package com.ch.model1.dto;

import java.util.List;
import lombok.Data;

// 검색 결과 배열 부분: "items": { "item": [ {...}, {...} ] }
@Data
public class Items {
    // JSON 구조상 "item"이 배열이라서 List<Item>으로 표현
    private List<Item> item;
}