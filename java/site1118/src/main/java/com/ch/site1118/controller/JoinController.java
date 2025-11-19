package com.ch.site1118.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 클라이언트가 전송한 파라미터들을 받아서 오라클로 받기 
// 클라이언트의 요청이 웹브라우저이므로 웹상의 요청을 받을 수 있고 오직 서버에서만 실행될 수 있는 클래스인 서블릿으로 정의 

public class JoinController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter(); //돌려주고싶으면 이걸로 . 
//	이걸로 곧바로 전송이 아니라 응답이 마무리되는 시점에 Tomcat과 같은 컨테이너 서버가 out.print에누적되어있는 문자열 대상으로 새로운 html문서를 작성할 때 사용됨 
		out.println("<h1>Hi ?</h1>");

		String id = request.getParameter("id"); // html에 있는 네임이랑 맞추기 
		String pwd = request.getParameter("pwd"); 
		String name = request.getParameter("name"); 
		
		out.println("전송받은 아이디는 " + id);
		out.println("전송받은 패스워드는 " + pwd);
		out.println("전송받은 이름은 " + name);
		
		String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
		String user = "servlet";
		String pass = "1234";

		Connection con	= null;
		PreparedStatement pstmt = null ; 
		
//		드라이버가 있어야 오라클을 제어할 수 있다. 따라서 드라이버 jar파일을 클래스패스에 등록 
//		IDE있으니까 또 lib파일에 jar 넣어라 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("로드성공");
			
//			오라클
			con = DriverManager.getConnection(url, user, pass);
//			접속성공여부는 connection이 null인지 판단 ! 
			if(con==null) {
				System.out.println("접속실패 ");
			}else {
				System.out.println("접속성공");}
		} catch (ClassNotFoundException e) {
			System.out.println("로드실패");
		} catch (SQLException e) {
		    System.out.println("DB 연결 실패! URL, 계정, 비밀번호, DB 실행 상태 확인.");
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
		

	}
	

}
