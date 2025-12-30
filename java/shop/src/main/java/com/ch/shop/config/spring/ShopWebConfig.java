package com.ch.shop.config.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ch.shop.dto.OAuthClient;

@Configuration // 단지 xml 을 대신한 설정용 클래스에 불과해!!
@EnableWebMvc // 필수 설정( 스프링이 지원하는 MVC 프레임워크를 사용하기 위한 어노테이션)

@ComponentScan(basePackages = { "com.ch.shop.controller.shop"})
public class ShopWebConfig extends WebMvcConfigurerAdapter {
	
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	//구글~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Bean
	public String googleClientId(JndiTemplate jndiTemplate) throws Exception{
		return (String)jndiTemplate.lookup("java:comp/env/google/client/id"); 
	}
	@Bean
	public String googleClientSecret(JndiTemplate jndiTemplate) throws Exception{
		return (String)jndiTemplate.lookup("java:comp/env/google/client/secret"); 
	}
	
	//네이버~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Bean
	public String naverClientId(JndiTemplate jndiTemplate) throws Exception{
		return (String)jndiTemplate.lookup("java:comp/env/naver/client/id"); 
	}
	@Bean
	public String naverClientSecret(JndiTemplate jndiTemplate) throws Exception{
		return (String)jndiTemplate.lookup("java:comp/env/naver/client/secret"); 
	}
	
	//Oauth로그인시 사용되는 환경변수는 객체로 담아 관리하면 유지하기 좋음. 여러프로바이더 연동하니 보관해놓자 OAuthClient객체를 여러개 메모리 보관 
	@Bean
	public Map<String, OAuthClient> oauthClients(
			@Qualifier("googleClientId") String googleClientId,
			@Qualifier("googleClientSecret") String googleClientSecret,
			@Qualifier("naverClientId") String naverClientId,
			@Qualifier("naverClientSecret") String naverClientSecret
			){
		//구글네이버카카오 각각 OAuthClient 인스턴스 담은 후 다시 Map에 모아두자 
		Map<String, OAuthClient> map = new HashMap<String, OAuthClient>();
		//구글 등
		OAuthClient google = new OAuthClient();
		google.setProvider("google");
		google.setClientId(googleClientId);
		google.setClientSecret(googleClientSecret);
		google.setAuthorizeUrl("https://accounts.google.com/o/oauth2/v2/auth");
		google.setTokenUrl("https://oauth2.googleapis.com/token");
		google.setScope("openid email profile"); //사용자 정보 접근범위 
		google.setRedirectUri("http://localhost:8888/login/callback/google");
		google.setUserInfoUrl("https://openidconnect.googleapis.com/v1/userinfo");
		
		map.put("google",google);
		
		OAuthClient naver = new OAuthClient();
		naver.setProvider("naver");
		naver.setClientId(naverClientId);
		naver.setClientSecret(naverClientSecret);
		naver.setAuthorizeUrl("https://nid.naver.com/oauth2.0/authorize");
		naver.setTokenUrl("https://nid.naver.com/oauth2.0/token");
		naver.setScope("name email"); //사용자 정보 접근범위 
		naver.setRedirectUri("http://localhost:8888/login/callback/naver");
		naver.setUserInfoUrl("https://openapi.naver.com/v1/nid/me");
		
		map.put("naver",naver);
		
		return map;
	};
	
	
	




}