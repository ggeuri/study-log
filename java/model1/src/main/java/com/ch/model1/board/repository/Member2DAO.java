package com.ch.model1.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ch.model1.dto.Board;
import com.ch.model1.dto.Member2;
import com.ch.model1.util.PoolManager;

public class Member2DAO {
	PoolManager pool = PoolManager.getInstance();
	//오직 데이터베이스 관련된 로직만 담당하는 DAO 클래스 
	//CRUD ! 
	public List<Member2> selectAll() {
		String sql = "select * from member2 order by member2_id";
		Connection con = pool.getConnection(); 
		PreparedStatement pstmt = null; 
		ResultSet rs = null ;                                                                               
		List<Member2> list = new ArrayList<Member2>();
		
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member2 member2 = new Member2(); 
				member2.setMember2_id(rs.getInt("member2_id"));
				member2.setId(rs.getString("id"));
				member2.setName(rs.getString("name"));
				member2.setEmail(rs.getString("email"));
				
				list.add(member2);				
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	
	
	public int insert(Member2 member2) {
		String sql = "insert into member2(id,name,email) values(?,?,?)";
		Connection con = null; 
		PreparedStatement pstmt = null; 
		int result = 0 ;                                                                                       
		
		con = pool.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member2.getId());
			pstmt.setString(2, member2.getName());
			pstmt.setString(3, member2.getEmail());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		return result; 
	}

}
