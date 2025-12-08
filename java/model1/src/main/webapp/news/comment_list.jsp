<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.fasterxml.jackson.annotation.JsonIgnoreProperties"%>
<%@page import="com.ch.model1.dto.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.mysql.cj.protocol.a.NativeConstants.IntegerDataType"%>
<%@page import="com.ch.model1.board.repository.CommentDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! CommentDAO commentDAO = new CommentDAO(); %>
<%
//특정뉴스기사에 딸려있는 코멘트 게시물 모두 가져오기 
// String sql = "select * from comment where news_id=?"
int news_id = Integer.parseInt(request.getParameter("news_id"));

//결과로 반환리스트 순수 데이터형태로 보내주기 (비동기니까)
List<Comment> commentList = commentDAO.selectByNewsId(news_id);

/* 
out.print(commentList);
 이렇게 그냥 보내버리면 문자열로 감 Json형태도 아님 파싱도안됨. JSON형태의 문자열인 JSON으로 보내야함 
 잭슨 라이브러리 활용하면 객체와 JSON 문자열간 변환 자동처리 (JS도 JSON내장객체 있음 )*/
 ObjectMapper mapper = new ObjectMapper(); 
 String json = mapper.writeValueAsString(commentList);
 
out.print(json);
 
%>