package com.ch.mvcframework.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.mvcframework.dto.Board;
import com.ch.mvcframework.repository.BoardDAO;

public class ListController implements Controller{
	BoardDAO boardDAO = new BoardDAO();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Board> list = boardDAO.selectAll();
		
		request.setAttribute("list", list);
		
		
		
	}

	@Override
	public String getViewName() {
		//현재 컨트롤러에서는 디자인 관련한 응답이나 재접속하라는 응답정보 보내면 안됨 (디스패처가 할 일) 
		// 하위컨트롤러는 디스페처한테 뷰페이지에 대한 정보만 반환 
		// 전달시 .jsp명시하지않는이유 = 하드코딩 지양, 유지보수목적  
		return "/board/list/result";
	}

	@Override
	public boolean isFoward() {
		// jsp까지 살려서 가져갈데이터있다? 포워딩 
		return true;
	}
	

}
