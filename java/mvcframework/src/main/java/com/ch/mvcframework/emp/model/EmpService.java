package com.ch.mvcframework.emp.model;

import org.apache.ibatis.session.SqlSession;

import com.ch.mvcframework.dto.Emp;
import com.ch.mvcframework.exception.EmpException;
import com.ch.mvcframework.mybatis.MybatisConfig;
import com.ch.mvcframework.repository.DeptDAO;
import com.ch.mvcframework.repository.EmpDAO;

public class EmpService {
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
	DeptDAO deptDAO = new DeptDAO();
	EmpDAO empDAO = new EmpDAO();
	// 애플리케이션 영역 중 서비스 정의
	// 서비스 정의목적 : 컨트롤러를 DAO와 분리.트랜잭션 대신 처리할 객체 필요

	// 한명의 사원
	public void regist(Emp emp) {
		SqlSession sqlSession = mybatisConfig.getSqlSession();

		// mybatis는 오토커밋 =false라서 트랜잭션시작알릴필요없음
		try {
			deptDAO.insert(sqlSession, emp.getDept());
			empDAO.insert(sqlSession, emp);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(); // 둘중 누구 잘못이든간에 롤백
			throw new EmpException("사원등록실패", e);
		} finally {
			mybatisConfig.release(sqlSession);

		}
	}
}
