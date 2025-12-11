package com.ch.mvcframework.repository;

import org.apache.ibatis.session.SqlSession;

import com.ch.mvcframework.dto.Emp;
import com.ch.mvcframework.exception.EmpException;
import com.ch.mvcframework.mybatis.MybatisConfig;

public class EmpDAO {
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
	//한건 등록 
	public void insert(SqlSession sqlSession, Emp emp) throws EmpException{

		try {
			sqlSession.insert("Emp.insert",emp); 
		} catch (Exception e) {
			e.printStackTrace(); //에러 정보 개발자나 시스템관리자가 알수있도록 로그 
			throw new EmpException("사원등록실패", e); 
		}

	}

}
