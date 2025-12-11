package com.ch.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//일반? 추상? 인터페이스?  
// 추상 : 자식에게 추상메서드 구현강제할 수 있다는 장점있으나 자식클래스가 이미 누군가를 상속받았을경우 못씀 
// 인터페이스만 다중상속되지롱. 추상메서드랑 상수만 보유 
public interface Controller {
	//앞으로 이 인터페이스를 구현하는 모든 자식 객체한테 메서드 구현강제하니까 메서드명 통일가능 !
	//또, 자식마다 구현내용다르니까 메서드내용 없이 기준만 세움 
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public String getViewName();
	
	//하위 컨트롤러가 jsp까지 데이터 살려서 유지할 일이 있을 경우, 요청에 대한 응답하면 안됨 
	//반드시 포워딩 처리 ! 따라서 하위컨트롤러는 DispatcherServlet에게 해당요청이 포워딩 대상인지 아닌지 
	//판단을위한 논리값반환메서드 제공 
	public boolean isForward();
}
