package com.ch.site1118.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 오라클에 들어있는 회원의 목록을 가져와서 화면에 출력하기 위함. 
public class MemberList extends HttpServlet {
	String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1"; 
	String user = "servlet"; 
	String pass= "1234"; 
	
//	클라이언트인  브라우저가 목록을 달라고 요청할 것이기때문에 get 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		드라이버 로드
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		
		Connection con = null; 
		PreparedStatement pstmt = null; //쿼리문 수행객체 오직 커넥트 객체로부터 인스턴스 얻음 쿼리문은 접속전제 
		ResultSet rs = null ;  // 셀렉트문 결과 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("드라이버 성공!");
			
			try {
				con = DriverManager.getConnection(url,user,pass);
				
				if(con==null) {
					out.print("접속실패");
				} else {
					out.print("접속성공");
					
					String sql = "select * from member order by member_id";
					pstmt=con.prepareStatement(sql); //쿼리 수행객체 생성 
					
//					DML인 경우 Update Select문인 경우에는 select인 경우 레코드(표)를 네트워크로 가져와야하므로, 그 표 결과를 그대로 반영할 객체가 필요한데 
//					이 객체를 가리켜 ResultSet이라 함 
					
					rs = pstmt.executeQuery();
					
					
//					rs를 그냥 표 자체로 생각해도무방하나 rs 내 레코드 접근 위해서는 포인터 역할 해주는 커서 제어해야함.rs가 생성되자마자 
//					생성 즉시엔 어떠한 레코드도 가리키지 않은 상태이므로, 개발자가 첫번째 레코드를 접근하려면 포인터 한칸 내려야함  . 
					 // 기존 커서의 위치보다 한칸 전진 
					
					StringBuilder tag = new StringBuilder();

					tag.append("<table width=\"100%\" border = \"1px\">");
					tag.append("<thead>");
					tag.append("<tr>");
					tag.append("<th>No</th>");
					tag.append("<th>ID</th>");
					tag.append("<th>Pwd</th>");
					tag.append("<th>Name</th>");
					tag.append("<th>Regdate</th>");
					tag.append("<th>Email</th>");
					tag.append("</tr>");
					tag.append("</thead>");
					tag.append("<tbody>");
					
			
					//반복문으로모든 레코드를 출력하기 
	                while (rs.next()) {
	                	int memberid = rs.getInt("member_id");    
	                	String id = rs.getString("id");     
	                    String pwd = rs.getString("pwd");
	                    String name = rs.getString("name");
	                    String regdate = rs.getString("regdate");
	                    String email = rs.getString("email");
	                   
	                    tag.append("<tr>");
	                    tag.append("<td>").append(memberid).append("</td>");
	                    tag.append("<td>").append(id).append("</td>");
	                    tag.append("<td>").append(pwd).append("</td>");
	                    tag.append("<td>").append(name).append("</td>");
	                    tag.append("<td>").append(regdate).append("</td>");
	                    tag.append("<td>").append(email).append("</td>");
	                    tag.append("</tr>");
	                }
	                
	              tag.append("</tbody>");
	              tag.append("</table>");
	              
	              out.print(tag.toString());
	              out.print("<a href='/member/join.html'>가입하기</a>");
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			out.print("실패");
			e.printStackTrace();
		}finally {
			if(rs != null)
			{try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //쿼리문 수행객체 오직 커넥트 객체로부터 인스턴스 얻음 쿼리문은 접속전제 				
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 				
			}
		}
		

	}
	

}
