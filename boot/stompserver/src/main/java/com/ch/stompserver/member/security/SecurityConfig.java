package com.ch.stompserver.member.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/*
  [SecurityConfig]
  - 스프링 시큐리티 전체 동작(인증/인가/필터)을 설정으로 제어하는 클래스
*/
@Configuration
public class SecurityConfig {

    /*
      [1] PasswordEncoder
      - 시큐리티는 비밀번호 비교 시 PasswordEncoder가 필요
      - NoOp = "평문 비교"(개발용) / 운영에서는 BCrypt 같은 해시 인코더 사용
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /*
      [2] AuthenticationManager
      - 로그인 인증(아이디/비번 검증)을 실제로 수행하는 핵심 매니저
      - 스프링이 구성한 AuthenticationManager를 꺼내 Bean으로 노출
    */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception{
        return config.getAuthenticationManager();
    }


    /*
      [3] CORS 설정
      - 브라우저는 Origin(출처)이 다르면 요청/응답을 제한함
      - 허용할 출처/메서드/헤더/쿠키 포함 여부를 서버가 명시해야 함
      - /api/** 경로에만 CORS 정책 적용
    */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();

        // 허용 Origin(프론트 주소). 필요하면 리스트에 추가
        config.setAllowedOrigins(List.of("http://localhost:7777"));

        // 허용 HTTP 메서드
        config.setAllowedMethods(List.of("PUT","OPTIONS","POST","GET","DELETE"));

        // 허용 헤더(개발용 전체 허용). 운영은 필요한 헤더만 지정 권장
        config.setAllowedHeaders(List.of("*"));

        // 쿠키/세션을 주고받으려면 true 필요(자격증명 포함)
        config.setAllowCredentials(true);

        // 같은 조건의 preflight(OPTIONS) 결과를 캐시하는 시간(초)
        config.setMaxAge(3600L);

        // 어떤 URL 패턴에 CORS를 적용할지 등록. 임포트할 때 스프링없는 걸로 임포트할 것
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    /*
      [4] SecurityFilterChain
      - 어떤 URL을 허용/차단할지(인가) 규칙 정의
      - "/" 와 "/api/auth/**"(로그인/인증 요청)는 누구나 접근 허용
      - 그 외 요청은 로그인(인증)된 사용자만 허용
      - formLogin 비활성화: 서버 기본 로그인 화면 대신 React/fetch 기반 로그인 사용
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors(cors->{});
        http.csrf(csrf->csrf.disable()); // 사이트 변조 공격 방지 비활성화

        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/", "/ws/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
        );

        // 기본 폼 로그인(서버 렌더링 로그인 페이지) 사용 안 함
        http.formLogin(form->form.disable());

        return http.build();
    }

}