package com.ch.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ch.mvcframework.food.model.FoodManager;
import com.ch.mvcframework.movie.model.MovieManager;
/*음식에 대한 판단 요청을 처리하는 컨트롤러
 * MVC - 개발이론, 방법론
 * model2 - 그 이론을 javaee 기술로 구현해놓은 모델
 * 			M - java 순수 클래스
 * 			V - JSP, HTML
 * 			C - 1) 웹서버에서 실행될 수 있어야 한다
 * 				2) 클라이언트의 요청을 받을 수 있어야 한다. -> 서블릿
 * model2의 컨트롤러 요건
 * */ 
public class FoodController implements Controller {
	FoodManager manager = new FoodManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String food = request.getParameter("food");
		
		String msg = manager.getAdvice(food);

		request.setAttribute("msg", msg);
		
		request.getRequestDispatcher("/food/result.jsp").forward(request, response);//포워딩하고싶은 url.forword로 포워딩발생 
		
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
