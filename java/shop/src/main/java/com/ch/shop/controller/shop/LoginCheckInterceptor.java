package com.ch.shop.controller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
- 회원 전용 서비스는 "로그인 여부"를 세션으로 체크해야 한다.
- 미로그인 상태면 로그인 폼으로 강제 유도(redirect/forward)한다.
- 컨트롤러마다 세션 체크를 중복 작성하지 말고, Spring Interceptor로 공통 처리한다.
*/

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 컨트롤러 실행 "직전"에 가로채서(Interceptor) 로그인 여부를 검사하는 메서드
		// return true -> 계속 진행(컨트롤러로 전달)
		// return false -> 여기서 요청 처리 중단(컨트롤러로 안 감)

		// 현재 요청과 연계된 세션 얻기 (주의: getSession()은 세션이 없으면 새로 만들어 반환함)
		HttpSession session = request.getSession();

		// 세션이 없거나(거의 없음), 세션에 로그인 정보(member)가 없으면 = 미로그인 상태로 판단
		if (session == null || session.getAttribute("member") == null) {

			// AJAX(비동기) 요청인지 확인하기 위해 관례적으로 쓰는 헤더를 읽음
			// X-Requested-With: XMLHttpRequest 이면 보통 "AJAX 요청"으로 간주
			String asyncHeader = request.getHeader("X-Requested-With");

			// (주의) asyncHeader가 null일 수 있으니 보통은 "XMLHttpRequest".equals(asyncHeader)로 NPE
			// 방지함
			if (asyncHeader != null && asyncHeader.equals("XMLHttpRequest")) {

				// AJAX 요청이면 redirect를 보내면 프론트가 HTML을 응답으로 받아서 깨질 수 있음
				// 그래서 JSON으로 응답을 내려주려는 의도로 Content-Type을 JSON으로 설정
				response.setContentType("application/json; charset=UTF-8");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 서버의 응답상태코드
				// \"msg\" : \"로그인이 필요한 서비스입니다\"
				response.getWriter().write("{\"msg\" : \"로그인이 필요한 서비스입니다\"}");

			} else {

				// 동기(일반 페이지 이동) 요청이면 로그인 폼으로 리다이렉트
				response.sendRedirect("/member/loginform");

			}
			// 리다이렉트 처리 후 컨트롤러로 넘어가지 않도록 요청 흐름을 끊음
			return false;
		}

		// (로그인 상태이거나, 위에서 막지 않았다면) 요청 계속 진행
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
