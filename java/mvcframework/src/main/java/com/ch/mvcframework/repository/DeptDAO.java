package com.ch.mvcframework.repository;

import org.apache.ibatis.session.SqlSession;

import com.ch.mvcframework.dto.Dept;
import com.ch.mvcframework.exception.DeptException;
import com.ch.mvcframework.mybatis.MybatisConfig;

public class DeptDAO {
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();

	// 한건 등록
	public void insert(SqlSession sqlSession, Dept dept) throws DeptException{
		
		try {
			sqlSession.insert("Dept.insert",dept); 
			
		} catch (Exception e) {
			e.printStackTrace(); //에러 정보 개발자나 시스템관리자가 알수있도록 로그 
			throw new DeptException("부서등록실패", e); 
		}

	}
}
	
