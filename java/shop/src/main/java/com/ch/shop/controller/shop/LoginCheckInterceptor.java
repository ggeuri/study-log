package com.ch.shop.controller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
- 회원 전용 서비스는 "로그인 여부"를 세션으로 체크해야 한다.
- 미로그인 상태면 로그인 폼으로 강제 유도(redirect/forward)한다.
- 컨트롤러마다 세션 체크를 중복 작성하지 말고, Spring Interceptor로 공통 처리한다.
*/

public class LoginCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//현재 요청에 연계된 세션얻기 
		HttpSession session = request.getSession(); 
		if(session==null || session.getAttribute("member")==null) {
			response.sendRedirect("/member/loginform");
			return false;
		}
		//원래 요청 그대로 진행하고 싶다면 true, 진행막으려면 false 
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
