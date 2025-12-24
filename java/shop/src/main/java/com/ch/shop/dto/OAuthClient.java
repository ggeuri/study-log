package com.ch.shop.dto;

import lombok.Data;

//구글뿐만아니라 네이버 카카오 등 IDP 연동시 필요한 정보 담는 객체 
@Data
public class OAuthClient {
	private String provider; // 구분값 *카카오 , 네이버, 구글 
	private String clientId; //개발자 콘솔에서 앱 등록 시 발급받은 클라이언트 Id
	private String clientSecret; //개발자 콘솔에서 앱 등록 시 발급받은 클라이언트 시크릿(얘는 공개하믄안됨)
	private String authorizeUrl; //오너정보조회할때사용할 요청주소 
	private String tokenUrl; //개발자 콘솔에서 앱 등록 시 발급받은 클라이언트 시크릿(얘는 공개하믄안됨)
	private String userInfoUrl; //사용자정보조회할때 사용할 URL 주소 
	private String scope; //콜백받을 주소 
	private String redirectUri; //콜백받을 주소 
	

}
