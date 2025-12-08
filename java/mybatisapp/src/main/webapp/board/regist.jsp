<%@page import="com.ch.mybatisapp.repository.BoardDAO"%>
<%@page import="com.ch.mybatisapp.dto.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! BoardDAO boardDAO = new BoardDAO();%>
<%
request.setCharacterEncoding("utf-8");

String title= request.getParameter("title");
String writer= request.getParameter("writer");
String content= request.getParameter("content");

Board board = new Board();

board.setTitle(title);
board.setWriter(writer);
board.setContent(content);

// insert ~~~~ 
int result = boardDAO.insert(board);

if(result < 1){
	out.print("등록실패");
}else { 
	out.print("등록성공");
}

%>