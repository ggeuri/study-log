package com.ch.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.mvcframework.dto.Board;
import com.ch.mvcframework.repository.BoardDAO;

public class RegistController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	//글쓰기 요청 처리하는 하위 컨트롤러 
	
	
	//	요청받는다 (디스패처서블릿)
	//	분석한다 (디스패처서블릿)
	//	맞는 로직 객체에 일시킨다 (하위컨트롤러가 하는 일)
	//	결과를 저장한다 (하위컨트롤러가 하는 일 : request로 저장, 전달!)
	//	알맞는 view페이지 보여준다 
 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title"); 
		String writer = request.getParameter("writer"); 
		String content = request.getParameter("content"); 
		int result = 0 ;
		Board board = new Board();
		
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		result = boardDAO.insert(board);
		//등록 후 성공시 목록 보여줘야함 이거 디스패처로 옮겨감 
//		response.sendRedirect("/board/list.jsp");
	}
	
	//DispatcherServlet이 보여줘야할 페이지 정보를 반환 
	public String getViewName() {
		return "/board/regist/result";
	}

	@Override
	public boolean isForward() {
		// 얘는 false일까 true일까? 
		return false; // 글 쓴 후에는 false다 !포워딩아니고 재접속해라 
	}
	

}
