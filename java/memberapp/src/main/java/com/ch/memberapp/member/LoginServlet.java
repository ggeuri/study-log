package com.ch.memberapp.member;

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
import javax.servlet.http.HttpSession;

import com.ch.memberapp.util.ShaManager;

public class LoginServlet extends HttpServlet {
	String url = "jdbc:mysql://localhost:3306/java";
	String user = "servlet";
	String pass = "1234";

	Connection con;
	PreparedStatement pstmt; 
	ResultSet rs ; 

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
//		드라이버 로드
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(url,user,pass); 
			if(con == null ) {
				System.out.println("접속실패");
			}else { 
				System.out.println("접속성공");
				String sqlString = "select * from member where id=? and pwd=?";
			
				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, id);
				pstmt.setString(2, ShaManager.getHash(pwd));
				
				rs = pstmt.executeQuery();
				StringBuffer tag = new StringBuffer();
				tag.append("<script>");
					
				if(rs.next()) {
					// 로그인 인정됐으니 한칸 움직인거 
					tag.append("alert('로그인 성공');");
					tag.append("location.href='/'"); //루트페이지라괜찮아용 
					
					
//					로그인 성공 회원은 브라우저끄지 않는 한 계속 기억효과를 내야함. 서버메모리에 회원정보 저장할 수있는 객체 올려야함. 
//					이러한 목적의 객체를 세션 객체라함. 생선된 세션 객체에는 자동으로 고유값 할당. 이를 세션 아이디라 한다. 
//					우리의 경우 로그인 성공 이후 회원에게 회원정보 기억효과를 내려면 회원 정보를 세션에 담으면 됨
//					그리고 담아진 정보는 사용자가 브라우저를 닫기 전까지는 계속 사용할 수 있음 (예외 - 서버에서 정해놓은 시간동안 재요청 없으면 자동 세션소멸 세션만료)  
					
					HttpSession session = request.getSession(); //톰캣 관리. 개발자가 직접 new할 수 없는 인터페이스  
//					주의할 점 . 세션은 브라우저가 들어올 때 무조건 생성되는 것이 아니라, 개발자가 아래의 세션을 건드리는 코드가 실행될 때 생성 
//					로그인 아닌데 만들필요없으니까 
					String sessionId = session.getId(); // 현재 세션에 자동으로 발급된 고유값
					
					out.print(sessionId); 
					
					Member member = new Member();
					member.setMember_id(rs.getInt("member_id"));
					member.setId(rs.getString("id"));
					member.setPwd(rs.getString("pwd"));
					member.setName(rs.getString("name"));
					member.setRegdate(rs.getString("regdate"));
					
//					회원 1명에 대한 정보가 채워진 DTO의 인스턴스를 세션에 담아두자(브라우저를 끌때까지는 회원정보를 꼐속 보여줄 수 있따.) 
//					HttpSession은 Map을 상속받음 , 따라서 Map형이다. 
//					Map은 자바의 컬렉션 프레임웤(자료구조)  컬렉션 프레임웤 목적? 다수데이터 중 오직 객체만을 대상으로 
//					효율적으로 데이터를 처리하기 위해 지원되는 자바의 라이브러리, java.util패키지에서 지원 
//					1. 순서있는 객체를 다룰 때 사용되는 자료형 (배열과 흡사) List 
//					2. 순서없는 객체를 다룰때 Set 
//					3. 순서없는 객체 중 특히 key-value의 쌍을 갖는 데이터 조합 - Map 
//					오전에 사용했던 js의 객체표기법 자체가 사실은 Map으로 구성됨 
//					let member={ name:"scott", age:30 } 
					
					session.setAttribute("member", member);
					
					
				}else {
					tag.append("alert('로그인 실패');");
					tag.append("history.back();");					
				}
				
			tag.append("</script>");
			out.print(tag.toString());
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		
		
		
		
//		접속
//		쿼리수행 
		
		
		
		
		
	}
}
