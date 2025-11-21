package com.ch.site1118.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
	
//	response.sendRedirect("/member/regist.html");
	
//	응답객체가 보유한 문자기반의 출력스트림에 개발자가 유저에게 전달하고싶은 메시지 보관 
	response.setContentType("text/html;");//브라우저에게 html임을 알림 
	response.setCharacterEncoding("utf-8");// 전세계모든언어 안깨지게 
	PrintWriter out = response.getWriter();
 //	MY SQL에 넣기 
	
//	java언어가 해당 데이터베이스 서버를 제어하려면 접속에 앞서 최우선으로 해당 DB제품을 핸들링할수 있는 
//	라이브러리인 jar 보유해야함 
	
	String url = "jdbc:mysql://localhost:3306/java";
	String user = "servlet";
	String pass = "1234";
	
	Connection con	= null;
	PreparedStatement pstmt = null ; 
			
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("로드성공");
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("로드실패");
	}
	try { 
//		보통은 jvm 이 자동으로 로드해주지만 개발자가 원하는 시점에 원하는 클래스 로드시킬 경우 
//	Class클래스가 static 메서드인 forName()메서드를 사용하기도 한다 
		con	=	DriverManager.getConnection(url, user, pass);
//		주의. jdbc에서 데이터베이스에 접속여부 판단할때 절대로 catch문에서 실패생각하면안
//		getConnection()메서드가 반환해주는 Connection인터페이스가 null인지 여부로 판
		if(con==null) {
			System.out.println("접속실패 ");
		}else {
			System.out.println("접속성공");
			
			
//			insert문 실행 
//			JDBC객체 중 쿼리수행을 담당하는 객체가 바로 PrepararedStatement 인터페이스다
//			그리고 이 객체는 접속을성공해야 얻을수있다 
			
		 
		pstmt = con.prepareStatement(
//				쿼리문 준비 ~ 
				"insert into member(id,pwd,name) values ('"+id+"','"+pwd+"','"+name+"')");
//			준비된 쿼리문을 실행하자 ~ 
		int result = pstmt.executeUpdate();
//		DML,메서드 실행 후 반환되는 값은 이 메서드에 의해 영향을 받은 레코드 수가 반환됨. 따라서 1보다 작은 수면 
//		이 쿼리에 의해 영향 받은 레코드 없는거니 수행실패임 ! 
		if(result<1) {
			System.out.println("등록실패 ");
			out.print("<script>");
			out.print("alert('등록실패');");
			out.print("</script>");
		}else {              
			System.out.println("등록성공");
//			웹브라우저에 성공 메시지 출력하기 ! 
			out.print("<script>");
			out.print("alert('등록완료');");
			out.print("</script>");
		}
	}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(pstmt!=null) {
			try {
				pstmt.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			if(con!=null) {
				try {
					con.close();				
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
	}
	
	
//	자바에서 DB를 다루는 기술을 가리켜 JDBC(Java DataBase Connectivity)라 한다. 
//	이 기술은 javaSE의 java.sql 패키지에서 주로 지원함 
//	현재 우리가 개발중인 분야가 javaEE라면 javaEE는 이미 javaSE를 포함하고 있다. 
	
	
	
	}
}
