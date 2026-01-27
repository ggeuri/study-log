package com.ch.securityapp.member.handler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

//UsernamePasswordAuthenticationFilter가 성공한 Authentication토큰 받은 경우 SecurityContext에 성공결과 저장
// -> 성공응답 처리하는 핸들라인 AuthenticationSuccessHandler
@Component
@Slf4j
public class JsonSuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("인증성공");
        response.setContentType("application/json;charset=UTF-8");//header
        response.getWriter().write("{\"ok\":true, \"name\":\""+authentication.getName()+"\"}");


    }
}
