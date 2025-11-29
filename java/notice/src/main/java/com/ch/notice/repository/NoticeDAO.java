package com.ch.notice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.ch.notice.domain.Notice;

// 이 클래스의 목적 : javaEE기반의 애플리케이션이든, javaSE기반의 애플리케이션 데이터베이스를 연동하는 비즈니스 로직은 동일하다 
// 따라서 유지보수성고려하여 여러 플랫폼에서 재사용할수있는 객체 정의 
// 따라서 유지보수성 고려하여 여러 플랫폼에서 재사용할 수 있는 객체 정의 
//특히 로직객체중 오직 데이터베이스 연동을 전담하는 역할을 하는 객체를 가리켜 애플리케이션 설계분야에서는 
// DAO(Data Access Object) - DB에 테이블 만일 5개면 DAO도 1:2대응 - 5개 
//특히 데이터베이스의 테이블에 데이터처리하는 업무를 CRUD (Create,Read,Update,Delete) 메서드 적어도 네개넹 

// 아래와 같은 메서드에서 매개변수 수가 많아질경우 코드 복잡해짐 
// 따라서 매개변수를 각각 낱개로 전달하는 것이 아니라 객체안에 모두넣어서, 객체자체전달 
// DTO (Data Transfer Object) 오직 데이터만 보유한 전달 객체  로직없음 (dummy object) 
public class NoticeDAO {
	public int regist(Notice notice) {
		String url = "jdbc:mysql://localhost:3306/java";
		String user = "servlet";
		String pass = "1234";
		int result = 0 ; 

		Connection con =null ; // 지역변수는 컴파일러가 자동으로 초기화하지않으니 초기화필수 
		PreparedStatement pstmt = null ; 
		ResultSet rs = null ; 
		//드라이버 로드 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
			
			//접속
			con = DriverManager.getConnection(url,user,pass);
			
			if(con==null) {
				System.out.println("실패");}
			else {
				System.out.println("성공");		
				System.out.println(con);
				
				String sql = "insert into notice(title, writer, content) values(?,?,?)";
				
				pstmt = con.prepareStatement(sql); 
				pstmt.setString(1, notice.getTitle());
				pstmt.setString(2, notice.getWriter());
				pstmt.setString(3, notice.getContent());
				
				result = pstmt.executeUpdate();
				
			}
			
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("드라이버로드실패 ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return result; 
		
	
		//쿼리실행
		//자원해제 
	}



}
