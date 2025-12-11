package com.ch.mvcframework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//디스패처가 요청 처리하기 전에 인코딩 처리를 미리하기 위함 
public class CharacterEncodingFilter implements Filter{
	String encoding ; 
	public void init(FilterConfig config) {
		encoding = config.getInitParameter("encoding");
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		
		//개발자가 필터 재정의하면서 필터 체인 흐름을 다시 원활하게 처리해야 함. 안그러면 필터에서 멈춤  
		chain.doFilter(request, response); // 흐름 그대로 갈 수 있도록 
		
	}

}
