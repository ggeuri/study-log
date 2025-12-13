package com.ch.mvcframework.emp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.mvcframework.controller.Controller;
import com.ch.mvcframework.dto.Dept;
import com.ch.mvcframework.dto.Emp;
import com.ch.mvcframework.emp.model.EmpService;

//사원등록요청 처리 하위 컨트롤러 
public class RegistController implements Controller {
	private EmpService empService = new EmpService();
	private String viewName;
	// 같은 트랜잭션으로 묶으려면 각각의 DAO가 공통 sqlSession사용해야함
	// MybatisConfig로부터 SqlSession 하나 취득해서 호출시 나눠주자

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dept2
		String deptno = request.getParameter("deptno");
		String dname = request.getParameter("dname");
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
		emp.setDept(dept);

		// 모델 영역에 일 시키기 (코드 혼재시 모델영역 분리시킬 수 없어 재사용성이 떨어짐)
		//아래의 regist() 메서드에는 호출자에게 예외를 전가시키는 throws는 런타임에러. 
		//하지만 처리는 해야 올바르게 실행된다 
		try {
			empService.regist(emp);
			viewName="/emp/regist/result";
			
		} catch (Exception e) {
			viewName="/emp/error";

		}
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return viewName;
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}

}
