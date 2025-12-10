package com.ch.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ch.mvcframework.movie.model.MovieManager;

public class MovieController implements Controller{
	MovieManager manager = new MovieManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String movie = request.getParameter("movie");
		 
		String msg = manager.getAdvice(movie);
		//위의 판단 결과를 여기서 출력하면 MVC위배임. 따라서 판단결과를 별도의 디자인 영역에서 보여줘야함 
		
		//Session으로 해보면..? 쿠키로 session Id함께 전송되니까 
		//쿠키는 영구(persistence) 세션(메모리). 서버에서 발급한 쿠키는 세션임  
		//		HttpSession session = request.getSession();
		//		session.setAttribute("msg", msg); 
		//세션도 되긴하는데.........메모리낭비니 쓰지말자 
	
		//포워딩. 현재들어온 요청에 대해 응답하지 않은상태로 다른 서블릿에 요청전달 
		//지정된 result.jsp의 서블릿의 service()메서드 호출 
		request.setAttribute("msg", msg);
		
		//포워딩하고싶은 자원 
		request.getRequestDispatcher("/movie/model2/result.jsp").forward(request, response);
		
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFoward() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
