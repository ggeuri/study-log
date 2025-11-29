package com.ch.memberapp.member;

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

import com.ch.memberapp.util.ShaManager;

// 회원가입 요청 처리하는 서블릿 
public class RegistServlet extends HttpServlet{
    Connection con ; 
    PreparedStatement pstmt ; 
    
	String url = "jdbc:mysql://localhost:3306/java";
	String user = "servlet";
	String pass = "1234";

    @Override
    protected void doPost(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        requset.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        
        String id = requset.getParameter("id");
        String pwd = requset.getParameter("pwd");
        String name = requset.getParameter("name");
        
        pwd = ShaManager.getHash(pwd) ;
        
        
//      넘겨받은 파라미터 중 비밀번호는 암호화시켜 DB insert

// 드라이버 로드 
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(url,user,pass);
			if(con != null) {
				out.print("접속성공");
				
				String sql = "insert into member(id,pwd,name) values(?,?,?)";
				
				pstmt = con.prepareStatement(sql); 
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				pstmt.setString(3, name); 
				
				int result = pstmt.executeUpdate();
				
				StringBuffer tag = new StringBuffer(); 
				tag.append("<script>");
				
				if(result < 1 ) {
					tag.append("alert('가입실패');");
					tag.append("history.back();");
					
				}else {
					tag.append("alert('가입성공');");
					tag.append("location.href='/member/login.jsp';");
//				가입이메일발송 	
				}
				tag.append("</script>");
				out.print(tag.toString()); // 스트림에 스크립트 담아놓기, 추후 고양이가 이 스트림 보고 코딩 . html 컨텐츠 생성 후 전송 
				
								
			}else {
				out.print("접속실패");
			}
			
//			tag.append("</script>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
// 접속 
 catch (SQLException e) {
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