package com.ch.shop.controller.shop;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ch.shop.dto.GoogleUser;
import com.ch.shop.dto.OAuthClient;
import com.ch.shop.dto.OAuthTokenResponse;
import com.ch.shop.dto.TopCategory;
import com.ch.shop.model.topcategory.TopCategoryService;

import lombok.extern.slf4j.Slf4j;

//일반유저 사용하게 될 회원관련 요청 처리 컨트롤러 
@Controller
@Slf4j
public class MemberController {
	@Autowired
	private TopCategoryService topCategoryService;
	@Autowired
	private Map<String, OAuthClient> oauthClients;
	@Autowired
	private RestTemplate restTemplate;
	//회원 로그인 폼 요청 처리 
	@GetMapping("/member/loginform")
	public String getLoginForm(Model model) {
		List<TopCategory> topList = topCategoryService.getList();
		model.addAttribute("topList", topList);
		return "shop/member/login";
	}
	
	//sns로그인을 희망하는 유저들의로그인 인증 요청 url을 알려주는 컨트롤러 메서드
	//@PathVariable("provider")는 url 일부를 파라미터화 시키는 기법 REST API에 사용됨 
	@GetMapping("/oauth2/authorize/{provider}")
	@ResponseBody
	public String getAuthUrl(@PathVariable("provider") String provider) throws Exception{
		OAuthClient oAuthClient = oauthClients.get(provider);
		
		log.debug(provider+"의 로그인인증 url은"+oAuthClient.getAuthorizeUrl());
		
		//이 주소를 이용하여 브라우저 사용자는 프로바이더에게 로그인을 요청해야하는데 이때 요청 파라미터를 갖추어야 로그인 성공
		//요청 시 지참할 파라미터에는 clientId, callback url, scope 
		StringBuffer sb = new StringBuffer();
		sb.append(oAuthClient.getAuthorizeUrl()).append("?")
		.append("response_type=code")
		.append("&client_id=").append(urlEncode(oAuthClient.getClientId()))
		.append("&redirect_uri=").append(urlEncode(oAuthClient.getRedirectUri()))
		.append("&scope=").append(urlEncode(oAuthClient.getScope()));
	
		return sb.toString(); 
		
	}
	
	//파라미터 전송시 문자열 안깨지게 
	private String urlEncode(String s) throws Exception{
		return URLEncoder.encode(s,"UTF-8");
	}
	
	//클라이언트가 동의화면(최초사용자) 또는 로그인(기존) 요청 provider가 이를 처리하는 과정에서 개발자가 등록해놓은 callback주소이용하여 임시코드 Authorize code 발급함 
	
	@GetMapping("/login/callback/google")
	public String handleCallback(String code) {
		//구글이 보내온 인증코드와 나의 ClientId, clientSecret 조합하여 token 요청
		//HTTP통신규약지켜서 말걸때는 머리 몸 구성해서 요청 
		
		log.debug("구글ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ" + code);
		//구글로부터 받은 임시코드와 나의정보(클라이언트아이디,시크릿) 
		OAuthClient google = oauthClients.get("google");
		MultiValueMap<String,String> param = new LinkedMultiValueMap<String, String>();
		param.add("grant_type","authorization_code");//임시코드이용하여 토큰요청하겠다 
		param.add("code",code);
		param.add("client_id",google.getClientId());
		param.add("client_secret",google.getClientSecret());
		param.add("redirect_uri",google.getRedirectUri());
		
		HttpHeaders headers = new HttpHeaders(); 
		//아래와같이 전송파라미터에 대한 타입명시하면 
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		//머리와 본문을 합쳐서 하나의 HTTP요청 엔터티로결합 (몸이 앞, 뒤가 머리 
		HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(param, headers);
		
		//구글에 요청시작. 스프링에서는 http요청 후 그 응답정보를 java객체와 자동 매핑해주는 객체 있음 = RestTemplate(http+jackson능력)
		ResponseEntity<OAuthTokenResponse> response = restTemplate.postForEntity(google.getTokenUrl(), request, OAuthTokenResponse.class);//받을주소. 머리몸합친요청객체,결과받을클래스 
		log.debug("구글로부터받은 응답정보" + response.getBody());
		
		//얻어진 토큰으로 구글에 회원정보 요청 
		OAuthTokenResponse responseBody = response.getBody();
		String access_token = responseBody.getAccess_token();
		log.debug(responseBody.getAccess_token());
		
		//회원정보가져오기 
		//얘도 HTTP통신규약지켜야함. 근데 토큰때는 몸은 필요없슴 
		HttpHeaders userInfoHeaders = new HttpHeaders(); 
		//헤더속성값넣ㄱ ㅣ
		userInfoHeaders.add("Authorization","Bearer "+access_token);
		HttpEntity<String> userInfoRequest = new HttpEntity<>("", userInfoHeaders);
		//얘는 가져오는거니까 GET 
		ResponseEntity<GoogleUser> userInfoResponse =restTemplate.exchange(google.getUserInfoUrl(), HttpMethod.GET,userInfoRequest,GoogleUser.class);//유저정보요청URL 
		 
		log.debug(""+userInfoResponse);
		
		//얻어진 유저정보이용하여 할일 1) 얻어진 회원이 mysql 존재하는지 따져서 있으면 세션부여 -> 메인으로 보내기 // 없으면? -> member테이블에 인서트+세션부여 +메인 
		
		return null;
	}
	
	
}
