package com.ch.model1.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ch.model1.dto.News;
import com.ch.model1.util.PoolManager;

// 뉴스 테이블에 대한 CRUD 수행하는 DAO
public class NewsDAO {
    PoolManager pool = PoolManager.getInstance();
    
    public int insert(News news) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = "insert into news(title, writer, content) values(?, ?, ?)"; 
        
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, news.getTitle());
            pstmt.setString(2, news.getWriter());
            pstmt.setString(3, news.getContent());
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
        
        return result;
    }
    
    // 목록 가져오기
    public List<News> selectAll() {
        List<News> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "select * from news order by news_id desc";
        
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                News news = new News();
               
                news.setNews_id(rs.getInt("news_id"));  
                news.setTitle(rs.getString("title"));
                news.setWriter(rs.getString("writer"));
                news.setContent(rs.getString("content"));
                news.setRegdate(rs.getString("regdate")); 
                news.setHit(rs.getInt("hit"));
                
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        
        return list;
    }
    
    public News select(int news_id) {
        News dto = null; 
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "select * from news where news_id = ?";
        
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, news_id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
               dto = new News();
               
                dto.setNews_id(rs.getInt("news_id"));  
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setContent(rs.getString("content"));
                dto.setRegdate(rs.getString("regdate")); 
                dto.setHit(rs.getInt("hit"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        
        return dto;
    }
}