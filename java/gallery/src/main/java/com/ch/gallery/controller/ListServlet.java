package com.ch.gallery.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 데이터베이스에 등록된 갤러리 목록을 출력하기 위한 서블릿 
public class ListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); 
		
//		태그...넣어봐.. ? 
// 서블릿은 서버에서 실행되는 javaEE기반의 클래스로서 서블릿없이는 javaee 개발 자체가 불가능 
//		하지만 디자인 페이지 작성 시 너무 비효율적이다 
//		따라서 servlet과 동일한 목적의 기술인 php,asp 등에 비해 경쟁력 떨어짐 
//   Servlet을 보완한 JSP사용하기 ...........
		
		

	}
	
}

