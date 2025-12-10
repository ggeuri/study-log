package com.ch.mvcframework.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ch.mvcframework.dto.Board;
import com.ch.mvcframework.mybatis.MybatisConfig;

//Model 
public class BoardDAO {
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
	//글 한 건 등록 
	public int insert(Board board) {
		int result = 0 ; 
		
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		result = sqlSession.insert("Board.insert", board);
		
		//SqlSession은 디폴트로 오토커밋속성이 false로 되어있으니 커밋해줘야함 
		sqlSession.commit();
		mybatisConfig.release(sqlSession);
		
		return result; 
		
	}
	
	public List<Board> selectAll() {
		List<Board> list = new ArrayList<Board>();
		
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		list = sqlSession.selectList("Board.selectAll");
		mybatisConfig.release(sqlSession);
		
		return list; 
		
	}
	
	public Board select(int board_id) {
		Board board = new Board();
		
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		board = sqlSession.selectOne("Board.select",board_id);
		mybatisConfig.release(sqlSession);
		
		return board; 
		
	}

}
