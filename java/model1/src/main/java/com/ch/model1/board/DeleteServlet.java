package com.ch.model1.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.model1.board.repository.BoardDAO;

public class DeleteServlet extends HttpServlet{
	BoardDAO boardDAO = new BoardDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		int result = boardDAO.delete(board_id);
		
		StringBuffer tag = new StringBuffer(); 
		tag.append("<script>");
		if(result<1) {
			tag.append("alert('삭제 실패');");
			tag.append("history.back();");
		}else { 
			tag.append("alert('삭제 성공');");
			tag.append("location.href='/board/list.jsp';");
		}
		tag.append("</script>");
		
		out.print(tag);

	}

}
