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

/* 글 한건 삭제요청을 처리하는 서블렛 정의
 * delete from notice where notice_id = 파라미터값
 * 
 * 프라이머리키(pk) 값은 내용이 길지 않으며, 보안상 중요하지 않기 때문에 get방식으로 받자! 
 * */
public class DeleteServlet extends HttpServlet {
	String url = "jdbc:mysql://localhost:3306/java";
	String user = "servlet";
	String pass = "1234";

	Connection con;
	PreparedStatement pstmt;// 쿼리문 수행객체 
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp에서의 page 지시영역과 동일한 교화를 주기 위한 코드 
		response.setContentType("text/html;charset=utf-8"); // MIME 타입 (브라우저가이해하는 형식을 작성해야 함)~ 이해못하면 걍 다운받는다! 
											  // image/jpg, application/json ...   
		
		// 클라이언트가 요청을 시도하면서 함께 지참해온 notice_id 파라미터값을 받자~
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter(); // 고양이가 응답 시 참고할 문자열을 모아둘 스트림! 
	
		String notice_id = request.getParameter("notice_id");
		System.out.println("넘겨받은 notice_id = "+notice_id); 

		// 드라이버 로드
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 접속
		try {
			con = DriverManager.getConnection(url,user,pass);
			if (con !=null) {

				// 쿼리문 날리기 
				String sql = "delete from notice where notice_id ="+notice_id;
				pstmt = con.prepareStatement(sql);
				
				int result = pstmt.executeUpdate();
				
				out.print("<script>");
				if (result < 1) {
					out.print("alert('삭제실패');"); // out으로JS할때 꼭 ; 넣기 
					out.print("history.back();");
				}else {
					out.print("alert('삭제성공');");
					out.print("location.href='/notice/list.jsp';");
				}
				out.print("</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { // 접속해제 
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
