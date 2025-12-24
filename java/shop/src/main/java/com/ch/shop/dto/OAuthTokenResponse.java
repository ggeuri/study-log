package com.ch.shop.dto;

import lombok.Data;

//이 객체 정의 목적 
// 프로바이더들에게 clientId, clientSecret, code 를 동봉해서 보내면 정보가 잘못되지 않았다면 Token보내줌 
// Token+각종다른정보 줘서.. 객체로 받아야함 다른정보도받을수있게 
@Data
public class OAuthTokenResponse {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;
	private String id_token;

}
