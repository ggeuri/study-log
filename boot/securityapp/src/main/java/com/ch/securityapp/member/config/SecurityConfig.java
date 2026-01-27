package com.ch.securityapp.member.config;

import com.ch.securityapp.member.filter.BeforeParameterFilter;
import com.ch.securityapp.member.handler.JsonSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

//스프링 시큐리티의 빈설정, 필터처리 흐름 제어할 수 있는 가장 중요한 클래스
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); //암호화시켰을때

        return NoOpPasswordEncoder.getInstance();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource source() {
        CorsConfiguration config = new CorsConfiguration();

        // 허용 Origin(프론트 주소). 필요하면 리스트에 추가
        config.setAllowedOrigins(List.of("http://localhost:5173"));

        // 허용 HTTP 메서드
        config.setAllowedMethods(List.of("PUT", "OPTIONS", "POST", "GET", "DELETE"));

        // 허용 헤더(개발용 전체 허용). 운영은 필요한 헤더만 지정 권장
        config.setAllowedHeaders(List.of("*"));

        // 쿠키/세션을 주고받으려면 true 필요(자격증명 포함)
        config.setAllowCredentials(true);

        // 같은 조건의 preflight(OPTIONS) 결과를 캐시하는 시간(초)
        config.setMaxAge(3600L);

        // 어떤 URL 패턴에 CORS를 적용할지 등록. 임포트할 때 스프링없는 걸로 임포트할 것
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return source;

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JsonSuccessHandler jsonSuccessHandler) throws Exception {
        http.cors(cors -> cors.configurationSource(source()));
        http.csrf(csrf -> csrf.disable());


        //테스트목적  인수(누구를,누구앞에)
        http.addFilterBefore(new BeforeParameterFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() //접근허용할 패턴
                .anyRequest().authenticated() //나머지 요청들에 대해 인증처리(로그인X -> 접근불가)
        );

        // 스프링이 기본으로 제공해주는 로그인폼은 안쓰고 필터체인만 씀
        http.formLogin(form -> form
                .loginProcessingUrl("/api/auth/login") //스프링이 지원하는 디폴트 로그인 요청 URL사용하지 않고 개발자가 원하는 것으로 변경
                .usernameParameter("homepageId")
                .passwordParameter("password")
                .successHandler(jsonSuccessHandler)

        );

        return http.build();
    }

}
