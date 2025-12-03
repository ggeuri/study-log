package com.ch.model1.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// DAO 각 메서드마다 커넥션풀로부터 Connection을 얻어오는 코드를 중복작성할 경우 유지보수성 떨어짐 
//따라서 앞으로는 커넥션풀로부터 커넥션을 얻거나 finally 반납하는 중복 코드는 아래의 클래스로 처리하면 유지보수성이 올라감 
public class PoolManager {
	private static PoolManager instance ; 
	DataSource ds ; 

	
	private PoolManager() {
		try {
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jndi/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	
	public static PoolManager getInstance() {
		if(instance==null) instance = new PoolManager();
		return instance ; 
	}
	//외부의 DAO 들이 직접 Connection 작성안하려면 여기서 Connection 얻어와서 반환 
	
	public Connection getConnection() {
		//클래스 변수인 instance 변수에 아무것도 존재하지 않을때는 아직 인스턴스 없는거니 그때 한번만 new 
		//싱글톤선언시 수많은 DAO들이 PoolManager를 매번 생성하는 낭비 방지가능 
		Connection con =null; 
		try {
			con =  ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return con; 
	}
	
	public void freeConnection(Connection con) {
		if(con!=null) {try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}	
	}
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		if(pstmt!=null) {try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}	

		if(con!=null) {try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}	
		
	}
	
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}	
	
		if(pstmt!=null) {try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}	
	
		if(con!=null) {try {
			//주의 : 기존 JDBC코드는 다 사용하면 닫았지만 풀로부터 얻어온 커넥션은 닫으면 안됨
			//이 객체는 DataSource 구현체로부터 얻어온 Connection이기때문에 일반적 JDBC close()가 아님 
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}	
		
		
	}
	

}
