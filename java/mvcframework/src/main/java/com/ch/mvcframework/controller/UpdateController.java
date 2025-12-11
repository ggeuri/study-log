package com.ch.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.mvcframework.dto.Board;
import com.ch.mvcframework.repository.BoardDAO;

public class UpdateController implements Controller {
    BoardDAO boardDAO = new BoardDAO(); 
    // 수정요청 

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String board_id= request.getParameter("board_id");
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String content = request.getParameter("content");
        Board board = new Board();
        
        board.setTitle(title);
        board.setWriter(writer);
        board.setContent(content);
        board.setBoard_id(Integer.parseInt(board_id));
        
        System.out.println(title + writer + board_id + content );
        
        boardDAO.update(board);

    }

    @Override
    public String getViewName() {
        return "/board/update/result";
    }

    @Override
    public boolean isForward() {
    	//오 이건신기..... 뷰매핑을 위해 포워드 (board_id?뒤 파라미터 못넘기니까)
        return true;
    }
}