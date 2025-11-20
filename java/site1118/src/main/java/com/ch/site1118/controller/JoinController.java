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

import com.ch.site1118.util.EmailManager;

// 클라이언트가 전송한 파라미터들을 받아서 오라클로 받기 
// 클라이언트의 요청이 웹브라우저이므로 웹상의 요청을 받을 수 있고 오직 서버에서만 실행될 수 있는 클래스인 서블릿으로 정의 

public class JoinController extends HttpServlet{
	EmailManager emailManager = new EmailManager(); 
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
		String email = request.getParameter("email"); 
		
		out.println("전송받은 아이디는 " + id);
		out.println("전송받은 패스워드는 " + pwd);
		out.println("전송받은 이름은 " + name);
		out.println("전송받은 메일은 " + email);
		
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
				System.out.println("접속성공");
				
				// 쿼리수행 PreparedStatement 인터페이스가 담당
				// JDBC는 데이터베이스 제품의 종류가 무엇이든 상관없이 DB를 제어할 수 있는 코드가 동일함.. (일관성 유지 가능)
				// 가능한 이유? 사실 JDBC 드라이버를 제작하는 주체는 벤더사이기 때문에.. 모든 벤더사는 java 언어를 제작한 오라클사에서 제시한
				// JDBC 기준 스펙을 따르기 때문에 가능하다.. 참고로 우리가 javaEE 시간에 별도의 개발툴킷을 설치할 필요가 없었던 이유는?
				// 오라클사는 javaEE 에 대한 스펙만을 명시하고, 실제 서버는 개발하지 않는다. 결국 javaEE 스펙을 따라 서버를 개발하는 벤더사들
				// 모두가 각자 고유의 기술로 서버를 개발하지만, 반드시 javaEE 에서 명시된 객체명을 즉 api명을 유지해야 하므로, 
				// 어떠한 종류의 서버이든 상관없이, 동일한 코드가 언제나 유지됨.
				
//				String sql="insert into member(member_id,id,pwd,name)\n"
//						+ "values(seq_member.nextval,'"+id+"', '"+pwd+"', '"+name+"'); ";
				String sql = "INSERT INTO member(member_id, id, pwd, name, email) "
				           + "VALUES (seq_member.nextval, ?, ?, ?, ?)";
				
				pstmt = con.prepareStatement(sql);
//			바인드 변수 쓰려면 물음표값이 뭔지 개발자가 PreparedStatement에 알려줘야함 
				pstmt.setString(1,id);
				pstmt.setString(2,pwd);
				pstmt.setString(3,name);
				pstmt.setString(4, email);
				
				int result= pstmt.executeUpdate();
				
				if(result!=0) {
					out.print("가입성공");
					
					emailManager.send(email);
					response.sendRedirect("/member/list");
//					회원목록페이지 보여주기 Select 문 말고 
					
					
				}else {
					out.print("실패");					
				}

			}
		} catch (ClassNotFoundException e) {
			System.out.println("로드실패");
		} catch (SQLException e) {
		    System.out.println("SQLException 발생");
		    System.out.println("에러 코드 : " + e.getErrorCode());
		    System.out.println("SQL 상태   : " + e.getSQLState());
		    System.out.println("메시지     : " + e.getMessage());
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
