package com.ch.shop.dto;

import lombok.Data;

//비동기요청시도시 응답정보가 메시지라면 jackson(자동컨버터)이용 
//컨버터를 이용하기 위해 응답정보 표현한 자바 클래스 필요 

@Data
public class ResponseMessage {
	private String msg; 
	
	
}
