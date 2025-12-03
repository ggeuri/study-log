package com.ch.model1.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ch.model1.dto.Board;
import com.ch.model1.util.PoolManager;

// 데이터베이스의 Board table에 대한 CRUD를 수행하는 객체 
public class BoardDAO {
	PoolManager pool = PoolManager.getInstance();
	 
	public List<Board> selectAll() {
		//커넥션 얻는 코드를 이 메서드에서 손수하지말자 PoolManager가 대신 해주니 
		Connection con = pool.getConnection(); // 풀매니저로부터 커넥션 객체 얻어옴 
		PreparedStatement pstmt = null ; 
		ResultSet rs = null ; 
		List<Board> list = new ArrayList<Board>(); // size 0인 상태 
		
		String sql = "select * from board"; 
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//rs는 무조건 이 메서드에서 닫아야하므로 외부의 jsp는 디자인 담당하는 코드. ResultSet의 존재 알필요 없고 
			//DB연동기술이므로 오직 DAO에서만 제어해야한다. 따라서 finally에서 rs 닫는것은 정답 
			// rs 닫아야하니까 list에 담아주자 
			// 1. 객체 필요 Board DTO
			// 2. Board DTO로부터 생성된 게시물을 표현한 인스턴스들을 모아놓을 객체 필요(순서필요)_
			
			while(rs.next()) {

			Board board = new Board();  // 게시물 한건 담을 수 있는 Board DTO클래스의 인스턴스 1개준비 
			board.setBoard_id(rs.getInt("board_id"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setRegdate(rs.getString("regdate"));
			board.setHit(rs.getInt("hit"));
			
			list.add(board);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt,rs);
		}
		return list ; 
	}
	
	//레코드 한건 
	public Board select(int board_id) {
		//쿼리 실행을 하기위한 데이터베이스 접속은 현재 코드에서 시도하말고 서버가동과 동시에 확보해놓은 커넥션풀로부터 가져오자 
		Connection con = pool.getConnection(); 
		PreparedStatement pstmt = null ; 
		ResultSet rs = null; 
		Board board = new Board();
		
		
		
		try {
			String sql = "select * from board where board_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,board_id);
			
			rs = pstmt.executeQuery(); 
			//rs가 죽어도 상관없으려면 게시물 1건을 표현할 수 있는 대체제 사용 
			//DB의 레코드 1건은 java DTO 인스턴스 1개와 매핑
			if(rs.next()) {
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
			
		}
		
		return board; 
		
	}
	
	//레코드 한건 수정 
	public int update(Board board) {
		Connection con =null; 
		PreparedStatement pstmt = null; 
		String sql = "update board set title=?, writer=?, content=? where board_id = ?";
		int result = 0; 
		
		con=pool.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getBoard_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);		
		}
		return result ;
		
	}

	public int insert(Board board) {
		//이 메서드 호출시마다 접속하는게 아니라 접속자 없어도 미리 Connection들을 확보해놓은 커넥션풀로부터 대여 
		//또한, 쿼리문 수행이 완료되면 얻어온 Connection은 절대로 닫지 말아야한다. 반납임 
		Connection con =null; 
		PreparedStatement pstmt = null; 
		int result = 0 ; 
		
		try {
			InitialContext context = new InitialContext();
			DataSource pool = (DataSource)context.lookup("java:comp/env/jndi/mysql"); //접두어 java:comp/env/ 
			con = pool.getConnection();
			
			String sql ="insert into board(title,writer,content) values(?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			result = pstmt.executeUpdate();
			
			//DAO니까 중립적이어야해용 . 디자인도 아니예용 

			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			//주의 : 기존 JDBC코드는 다 사용하면 닫았지만 풀로부터 얻어온 커넥션은 닫으면 안됨 . 
			if(con!=null)
				try {
					con.close();
				 //이 객체는 DataSource 구현체로부터 얻어온 Connection이기때문에 일반적 JDBC close()가 아님 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
		
		}
		return result ;
	}
	
	public int delete(int board_id) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		int result = 0 ; 
		
		String sql = "delete from board where board_id = ?";
		
		con = pool.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { 
			pool.freeConnection(con, pstmt);
		}
		
		return result; 
		
		
	}

}
