package com.ch.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.mvcframework.repository.BoardDAO;

public class DetailController implements Controller{
	BoardDAO boardDAO = new BoardDAO(); 
//게시물 1건 처리 하위 컨트롤러 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board= Integer.parseInt(request.getParameter("board_id"));
		boardDAO.select(board);
		
		System.out.println("DTO"+board);
		
		request.setAttribute("board", board);
		
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/board/detail/result";
	}

	@Override
	public boolean isFoward() {
		// TODO Auto-generated method stub
		return true;
	}
}