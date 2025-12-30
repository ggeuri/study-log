package com.ch.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//토큰이용하여 사용자정보 요청할때 서버측에서 보내온 정보 담게될 객체 
@Data
public class NaverUser {
	private String id; // 구글에서 id용어 쓰지않음 sub라는 키값으로 보내옴 어노테이션 명시해서 자동 매핑 
	private String email;
	private String name;
	private String nickname;
	private String profile_image;
	private String gender; 
	private String age; 
	private String birthday; 
	

}
