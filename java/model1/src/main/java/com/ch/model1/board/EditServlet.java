package com.ch.model1.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.model1.board.repository.BoardDAO;
import com.ch.model1.dto.Board;

public class EditServlet extends HttpServlet{
	BoardDAO boardDAO = new BoardDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		//파라미터 4개 넘겨받아 DAO에게 시키자 
		Board board = new Board();
		board.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		board.setTitle( request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
		
		int result = boardDAO.update(board);
		
		out.print("<script>");
		if(result<1) {
			out.print("alert('수정 실패');");
			out.print("history.back();");
		}else { 
			out.print("alert('수정 성공');");
			out.print("location.href='/board/detail.jsp?board_id=" + board.getBoard_id()+"';");
		}
		out.print("</script>");
	
	}

}
