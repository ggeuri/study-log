package com.ch.model1.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// DAO 각 메서드마다 커넥션풀로부터 Connection을 얻어오는 코드를 중복작성할 경우 유지보수성 떨어짐 
//따라서 앞으로는 커넥션풀로부터 커넥션을 얻거나 finally 반납하는 중복 코드는 아래의 클래스로 처리하면 유지보수성이 올라감 
public class PoolManager {
	DataSource ds ; 
	public PoolManager() {
		try {
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jndi/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	//외부의 DAO 들이 직접 Connection 작성안하려면 여기서 Connection 얻어와서 반환 
	
	public Connection getConnection() {
		Connection con =null; 
		try {
			con =  ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return con; 
	}
}
