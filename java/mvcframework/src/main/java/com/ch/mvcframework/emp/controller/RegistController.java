package com.ch.mvcframework.emp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.ch.mvcframework.controller.Controller;
import com.ch.mvcframework.dto.Dept;
import com.ch.mvcframework.dto.Emp;
import com.ch.mvcframework.mybatis.MybatisConfig;
import com.ch.mvcframework.repository.DeptDAO;
import com.ch.mvcframework.repository.EmpDAO;

//사원등록요청 처리 하위 컨트롤러 
public class RegistController implements Controller{
	// 같은 트랜잭션으로 묶으려면 각각의 DAO가 공통 sqlSession사용해야함 
	// MybatisConfig로부터 SqlSession 하나 취득해서 호출시 나눠주자 
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
	DeptDAO deptDAO = new DeptDAO(); 
	EmpDAO empDAO = new EmpDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dept2
		String deptno  = request.getParameter("deptno"); 
		String dname  = request.getParameter("dname"); 
		String loc = request.getParameter("loc"); 
		
		// Emp2 
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename"); 
		String sal = request.getParameter("sal"); 
		
		Dept dept = new Dept(); 
		dept.setDeptno(Integer.parseInt(deptno));
		dept.setDname(dname);
		dept.setLoc(loc);
		
		Emp emp = new Emp();		
		emp.setEmpno(Integer.parseInt(empno));
		emp.setEname(ename);
		emp.setSal(Integer.parseInt(sal));
		
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		
		//mybatis는 오토커밋 =false라서 트랜잭션시작알릴필요없음 
		try {
			deptDAO.insert(sqlSession, dept);
			empDAO.insert(sqlSession, emp);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(); //둘중 누구 잘못이든간에 롤백 
		}
		
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}

}
