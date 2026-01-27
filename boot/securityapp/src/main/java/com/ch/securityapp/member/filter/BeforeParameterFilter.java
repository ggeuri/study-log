package com.ch.securityapp.member.filter;

//스프링 시큐리티 요청 처리 흐름 파악하기 위해 UsernamePasswordAuthenticationFilter 상속받고
// 메서드 오버라이드해서 매개변수 찍어볼수있지만 필터체인 깨질 리스크 있어서 UsernamePasswordAuthenticationFilter 앞에
// 나만의 필터 두고 파라미터 낚아채기

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class BeforeParameterFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       //다른 요청 제외. 오직 로그인 요청에 대해서만 파라미터 확인
       if(request.getRequestURI().equals("/api/auth/login") &&"POST".equalsIgnoreCase(request.getMethod())){
           String homepageId = request.getParameter("homepageId");
           String password = request.getParameter("password");
           log.debug("클라이언트가 전송한 홈피아이디 = {}, 패스워드 = {}", homepageId, password);
       }
       //요청흐름이 원래 가던 길 갈수있도록 흐름 터줘야함. 안그러면 여기서 멈춤
       filterChain.doFilter(request,response);

    }
}
