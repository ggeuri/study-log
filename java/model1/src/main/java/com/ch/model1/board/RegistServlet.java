package com.ch.model1.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.model1.board.repository.BoardDAO;
import com.ch.model1.dto.Board;

public class RegistServlet extends HttpServlet{
	BoardDAO boardDAO = new BoardDAO(); // 서블릿의 생명주기에서 인스턴스는 최초 요청에 의해 1번만 생성. 서블릿의 멤버변수로 선언한 객체로 따라서 1번 생성  
	//RegistServlet has a BoardDAO 
	// 자바의 객체와 객체 사이의 관계를 명시할 때는 단 2가지 유형으로 나뉨 
	// 자바에서 특정 객체가 다른객체 보유 = has a 
	// 자바에서 특정 객체가 다른객체 상속 = is a  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 넘겨받은 파라미터를 이용하여 DB직접 넣는 것이 아니라 전담객체에게 시켜야함 :DAO (DB코드 재사용) 
		// 다른 로직은 포함시켜서는 안되며, 오직 DB관련된 CRUD만을 담당하는 객체를 가리켜 DAO(Data Access Object) 
		String title = request.getParameter("title"); 
		String writer =request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board board = new Board(); 
		
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content); 
		
		//DB연동 
		boardDAO.insert(board);
		
		response.sendRedirect("/board/list.jsp");
		
		
		
		}

}
