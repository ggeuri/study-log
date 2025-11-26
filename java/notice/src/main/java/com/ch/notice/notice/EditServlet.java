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

//수정요청처리 서블릿//수정내용폼의 데이터 규모가 크기 때문에 POST 
public class EditServlet extends HttpServlet{
	Connection con ;
	PreparedStatement pstmt ; 
	String url = "jdbc:mysql://localhost:3306/java";
	String user = "servlet";
	String pass = "1234";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); 
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String notice_id = request.getParameter("notice_id"); 
		
		out.println("title "+title);
		out.println("writer "+writer);
		out.println("content "+content);
		out.println("notice_id "+notice_id);
		String sql = "update notice set title =?,writer=?, content=? where notice_id=?";
		
		//드라이버 로드 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//접속 
		
		try {
			con = DriverManager.getConnection(url,user,pass);
			
			if(con != null) {
				out.print("접속성공");
				
				pstmt = con.prepareStatement(sql);
//				 title =?,writer=?, content=? where notice_id=?";
				pstmt.setString(1,title);
				pstmt.setString(2,writer);
				pstmt.setString(3,content);
				pstmt.setInt(4,Integer.parseInt(notice_id));
		
				
				int result = pstmt.executeUpdate();
				
				out.print("<script>");
				if (result < 1) {
					out.print("alert('수정실패');"); // out으로JS할때 꼭 ; 넣기 
					out.print("history.back();");
				}else {
					out.print("alert('수정성공');");
					out.print("location.href='/notice/detail.jsp?notice_id=" + notice_id + "';");
				}
				
			} else {
				out.print("실패"); 
			}
			out.print("</script>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			if(pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		

		
		
	}

}
