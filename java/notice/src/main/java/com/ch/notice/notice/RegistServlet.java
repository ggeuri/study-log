package com.ch.notice.notice;

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

//html로부터 글쓰기 요청받는 서블릿 정의 
//jsp도 사실 서블릿임. 현재 이 서블릿의 역할을 대신할수도있음
// 하지만 jsp자체가 서블릿의 디자인능력을 보완하기위해 나온기술이므로 
//현재 이 서블릿에서는 디자인 필요없어서 굳이 사용할 필요 없음 
public class RegistServlet extends HttpServlet{
	String url = "jdbc:mysql://localhost:3306/java";
	String user = "servlet"; 
	String password = "1234" ; 
	
	Connection con ; 
	PreparedStatement pstmt; 
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("요청감지");
		
		// 클라이언트가 전송한 파라미터를 받자 
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		PrintWriter out = response.getWriter(); 
		
		out.print(title + "<br>");
		out.print(writer + "<br>");
		out.print(content + "<br>");
		
		
//		mysql의 java db안에 notice에 insert ;; maven설치완  
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			out.print("드라이버로드성공" + "<br>");
			con = DriverManager.getConnection(url,user,password);
			
			if(con==null) {
				out.print("접속실패" + "<br>");
			}else {
				out.print("접속성공" + "<br>");
				
//				접속 성공됐으니 쿼리해봐라 
				String sql = "insert into notice(title,writer,content) values (?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, writer);
				pstmt.setString(3, content);
				
				int result = pstmt.executeUpdate();
				
				if(result < 1 ) {
					out.print("실패");
				} else { 
					out.print("성공");
					
					response.sendRedirect("/notice/list.jsp"); 
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("드라이버로드실패");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
