package com.ch.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//토큰이용하여 사용자정보 요청할때 서버측에서 보내온 정보 담게될 객체 
@Data
public class GoogleUser {
	@JsonProperty("sub")
	private String id; // 구글에서 id용어 쓰지않음 sub라는 키값으로 보내옴 어노테이션 명시해서 자동 매핑 
	private String email;
	private Boolean verified_email;
	private String name;
	private String given_name;
	private String family_name;
	private String picture; //프로필사진 url 
	private String locale; //언어설정 
	

}
