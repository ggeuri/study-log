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

public class test extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		PrintWriter out = resp.getWriter();
		
		out.print("전송받은 아이디는 " + id);
		out.print("전송받은 패스워는 " + pwd);
		out.print("전송받은 이름은 " + name);
		out.print("전송받은 메일은 " + email);
		
		String url ="jdbc:oracle:thin@//localhost:1521/XEPDB1";
		String user = "servlet"; 
		String pass = "1234";
		
		Connection con = null; 
		PreparedStatement prsmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("로성공");
			
			try {
				con = DriverManager.getConnection(url, user, pass);
				
				if(con!=null) {
					out.print("접속성공");
					
					String sql = "insert into member(member_id,id,pwd,name,email)"
							+ " values(seq_member.nextval, ?,?,?,?";
					prsmt = con.prepareStatement(sql);
					
					prsmt.setString(1, id);
					prsmt.setString(2, pwd);
					prsmt.setString(3, name);
					prsmt.setString(4, email);
					
					prsmt.executeUpdate(sql);
					
				} else {
					out.print("실패");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(prsmt != null) {
				try {
					prsmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con != null) {
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
