package com.ch.gallery.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.gallery.util.StringUtil;
import com.oreilly.servlet.MultipartRequest;

// 클라이언트의 업로드를 처리할 서블릿 
public class UploadServlet extends HttpServlet {
	String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
	String user = "servlet"; 
	String pass = "1234";
	//클라이언트 포스트 요청 처리 메서드 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
//	업로드 처리할 cos 컴포넌트를 사용해보자. MultipartRequest객체는 일반클래스이므로 , 개발자가 new 연상자를 이용하여 인스턴스를 
//		직접 생성할 수 있다. 따라서 이 객체가 지원하는 생성자 조사 후 사용 
		
//		MultipartRequest multi = new MultipartRequest(req, "/Users/rimu/upload");
		int maxSize = 1024*1024*3; 
		
//		4번째 생성자는 용량뿐만 아니라 파일명에 한글이 포함되어있어도 깨지지않도록 처리가 되어있음
//		용량은 byte단위가 기본 
		MultipartRequest multi = new MultipartRequest(req, "/Users/rimu/upload",maxSize,"utf-8");
		
//		클라이언트가 전송한 데이터 중 텍스트 기반의 데이터를 파라미터 이용하여 받아보기
//		클라이언트가 전송한 데이터 인코딩형식이 multipart/form-data 일 떄는 기존에 파라미터 받는 코드인 
//		request.getParameter()동작못함. 대신 업로드 처리한 컴포넌트통해 파라미터값을 추
		String title = req.getParameter("title");
		out.print("클라이언트전송제목"+title);
		
//		이미 업로드된 파일은 사용자가 정한 파일명이므로 웹브라우저에서 표현시 불안할 수 있음 
//		파일명을 개발자가 정한 규칙, 또는 알고리즘으로 변경한다 
//		방법 ) ex - 현재시간(ms 까지 표현 ), hash -16진수 
		long time = System.currentTimeMillis();
		
		out.println(time);
		out.println("<br>");
		out.println("업로드 성공");
		
//	방금 업로드된 파일명을 조사하여 현재 시간과 확장자 조합하여 새로운 파일명 만들기 
//		이미 업로드된 파일 정보는 파일컴포넌트 스스로 알고있음 우리의 경우 multi 
		out.println("<br>");
		String name = multi.getOriginalFileName("photo"); 
		out.print(name);
		out.println("<br>");
		
		String extend = StringUtil.getExtendFrom(name);
		out.print(extend);
		
//		파일명과 확장자를 구했으니, 업로드된 파일의 이름을 변경하자 
//		자바에서는 파일명을 변경하거나 삭제 등을 처리하려면 java.io.File클래스를 이용해야한다 
		File file = multi.getFile("photo");
		out.print("<br>");
		out.print(file);
		
//		File클래스메서드 중 파일명 바꾸는 메서드 사용 
		boolean result = file.renameTo(new File("/Users/rimu/upload/"+time+"."+extend));
		out.print("<br>");
		
		
		if (result) {
		    out.print("업로드성공<br>");

		    // 오라클 접속하여 인서트문 수행 
		    Connection con = null;
		    PreparedStatement pstmt = null;

		    try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");
		        con = DriverManager.getConnection(url, user, pass);

		        if (con == null) {
		            out.println("접속실패<br>");
		        } else {
		            out.println("접속성공<br>");

		            // DB에 저장할 데이터 준비
		            String newFileName = time + "." + extend;     // 변경한 파일명

		            String sql = "INSERT INTO  gallery(gallery_id, title, filename) "
		                       + "VALUES (seq_gallery.nextval, ?, ?)";

		            pstmt = con.prepareStatement(sql);//접속 객체로부터 쿼리수행 객체 인스턴스 얻디 
		            
//		            쿼리문 수행에 앞서 바인드 변수값 결정 
		            pstmt.setString(1, title);         // 1번째 ?
		            pstmt.setString(2, newFileName);   // 2번째 ?
		            
//		            DML이니까 executeUpdate() 사용해야함 
		            int resultCnt = pstmt.executeUpdate(); // INSERT 실행
		            if(resultCnt<1) {
		            	out.print("등록실패");
		            }else {
		            	out.print("DB Insert 결과 : " + resultCnt + "건<br>");
		            	
		            	resp.sendRedirect("/upload/list.jsp");
		            }
		            
		           
		        }

		    } catch (Exception e) {  // ClassNotFoundException + SQLException 모두 처리
		        e.printStackTrace();
		        out.println("DB 처리 중 오류 발생!");
		    } finally {
		        // 자원 정리
		        try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
		        try { if (con != null) con.close(); } catch (Exception e) { }
		    }

		} else {
		    out.print("업로드 실패");
		}
		
		
	}
	

}
