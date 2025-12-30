package com.ch.shop.dto;

import lombok.Data;

@Data
public class Member {
	private int member_id ; 
	private String home_id; // sns회원인 경우 null 
	private String home_pass; // sns회원인 경우 null 
	private String provider_userid; // 홈페이지회원인 경우 null 
	private String name; //provider측에서는 실명 주지 않음 = 닉네임 
	private String email;
	private String regdate  ;
	private String updated  ;
	private Provider provider; 
}
