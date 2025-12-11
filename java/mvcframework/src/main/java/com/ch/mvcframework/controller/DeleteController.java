package com.ch.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.mvcframework.dto.Board;
import com.ch.mvcframework.repository.BoardDAO;

public class DeleteController implements Controller {
    BoardDAO boardDAO = new BoardDAO(); 
    // 게시물 1건 삭제 하위 컨트롤러 
    
    //3단계 일시키기 , 4단계는 결과저장 (DML수행시 4단계 생략임) 4단계 생략은 view로 가져갈 것 없으니까 재접속해도된다 =포워드할거없다 

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String boardId = request.getParameter("board_id");
        
        int result =  boardDAO.delete(Integer.parseInt(boardId));
        
    }

    @Override
    public String getViewName() {
        return "/board/delete/result";
    }

    @Override
    public boolean isForward() {
        return false;
    }
}