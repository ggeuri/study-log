package com.ch.shop.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration // 단지 xml 을 대신한 설정용 클래스에 불과해!!
@EnableWebMvc // 필수 설정( 스프링이 지원하는 MVC 프레임워크를 사용하기 위한 어노테이션)

@ComponentScan(basePackages = { "com.ch.shop.controller.shop"})
public class ShopWebConfig extends WebMvcConfigurerAdapter {




}