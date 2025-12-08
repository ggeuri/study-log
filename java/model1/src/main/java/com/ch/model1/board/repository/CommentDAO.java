package com.ch.model1.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ch.model1.dto.Comment;
import com.ch.model1.dto.News;
import com.ch.model1.util.PoolManager;

//오직 comment 테이블에 대한 CRUD만을 수행하는 DAO 
public class CommentDAO {
	PoolManager pool = PoolManager.getInstance();
	//등록 
	
	public int insert(Comment comment) {
		int result = 0 ; 
		Connection con = null ; 
		PreparedStatement pstmt = null ; 
		String sql="insert into comment(msg,reader,news_id) values (?,?,?)"; 
		
		
		con = pool.getConnection(); 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, comment.getMsg());
			pstmt.setString(2, comment.getReader());
			pstmt.setInt(3, comment.getNews().getNews_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return result; 
	}
	
	public List<Comment> selectByNewsId(int news_id){
		 String sql = "select * from comment where news_id=?"; 
		Connection con = null ; 
		PreparedStatement pstmt = null ; 
		ResultSet rs = null; 
		List<Comment> list = new ArrayList<Comment>();
		
		con = pool.getConnection(); 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Comment comment = new Comment();
				News news = new News();
				comment.setComment_id(rs.getInt("comment_id"));
				comment.setMsg(rs.getString("msg"));
				comment.setReader(rs.getString("reader"));
				comment.setWritedate(rs.getString("writedate"));
				news.setNews_id(rs.getInt("news_id"));
				comment.setNews(news);
				
				list.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		 
		 return list ; 
		 
	}

}
