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
        
        con = pool.getConnection();
        
        String sql = "select * from news order by news_id desc";
        
        StringBuffer sb = new StringBuffer();
		sb.append(" select n.news_id as news_id, title, writer, regdate, hit, count(comment_id) as cnt");
		sb.append(" from news n LEFT outer join comment c");
		sb.append(" on n.news_id = c.news_id");
		sb.append(" GROUP by news_id, title, writer, regdate, hit"); 
		sb.append(" order by n.news_id desc");
		System.out.print(sb.toString());
        
        try {
            pstmt = con.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                News news = new News();
                news.setNews_id(rs.getInt("news_id"));  
                news.setTitle(rs.getString("title"));
                news.setWriter(rs.getString("writer"));
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
		// 쿼리 실행을 하기 위한 데이터베이스 접속은 현재 코드에서 시도하지 말고,
		// 서버 가동과 동시에 확보해놓은 커넥션풀로부터 가져오자!!!
		Connection con = pool.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		News news = null;
		
		
		try {
			String sql = "select * from news where news_id =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs = pstmt.executeQuery(); // select문 실행!!!
			
			// rs가 죽어도 상관없으려면, 게시물 1건을 표현할 수 있는 대체제를 사용해야 함
			// DB의 레코드 한 건은 java에서 DTO 인스턴스 1개와 매핑...
			if(rs.next()){ // next()가 true인 경우 즉 쿼리 실행에 의해 조건에 맞는 레코드가 존재할 때만 DTO를 반환하자
				News dto = new News(); // empty 텅빈 상태
				dto.setNews_id(rs.getInt("news_id"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setHit(rs.getInt("hit"));
				dto.setCnt(rs.getInt("cnt"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return news;
	}
    
    
}