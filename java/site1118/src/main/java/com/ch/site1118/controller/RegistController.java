package com.ch.site1118.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//회원 등록 요청을 처리할 서블릿클래스 
// HTTP 요청 방식 중 클라이트가 서버로 데이터를 전송해오는 방식은 POST방식 
// 따라서 HttpServlet이 보유한 doXXX형 메서드 중 doPost를 재정의해야함 

public class RegistController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 전송한 id, pw, name을 받아서 출력해보기 
	System.out.println("클라이언트의 post 요청감지 "); //웹브라우저 말고현재 톰캣 콘솔에 출력 
	
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");

	System.out.println("전송받은 아이디는 " + id);
	System.out.println("전송받은 패스워드는 " + pwd);
	System.out.println("전송받은 이름은 " + name);
		
//	MY SQL에 넣기 
	}
}
