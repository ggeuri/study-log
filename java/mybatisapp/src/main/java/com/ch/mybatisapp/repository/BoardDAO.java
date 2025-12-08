package com.ch.mybatisapp.repository;

import org.apache.ibatis.session.SqlSession;

import com.ch.mybatisapp.dto.Board;
import com.ch.mybatisapp.mybatis.MybatisConfig;

public class BoardDAO {
	MybatisConfig mybatisConfig  = MybatisConfig.getInstance();
	//글쓰기 
	
	public int insert(Board board) {
		int result = 0;
		//Mybatis에게 맡기자 
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		result = sqlSession.insert("com.ch.mybatisapp.dto.Board.insert",board);
		//DML은 트랜젝션 확정지어야한다 
		sqlSession.commit();
		mybatisConfig.release(sqlSession);
		
		return result;
	}

}
