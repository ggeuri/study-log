<%@page import="com.ch.model1.dto.News"%>
<%@page import="com.ch.model1.dto.Comment"%>
<%@page import="com.ch.model1.board.repository.CommentDAO"%>
<%@ page contentType="application/json; charset=UTF-8"%>
<%! CommentDAO dao = new CommentDAO(); %>
<% 
//클라이언트가 비동기적으로 요청 시도, 파라미터 받고 DB에 넣은 후 응답정보는 데이터 
//JSON으로 보내줘야댐 

request.setCharacterEncoding("utf-8");
String msg = request.getParameter("msg");
String reader = request.getParameter("reader");
String news_id = request.getParameter("news_id");
System.out.print(msg + reader + news_id);

Comment comment = new Comment();

comment.setMsg(msg);
comment.setReader(reader);
//부모를 숫자가 아닌 객체형태로 보유 
News news = new News();
news.setNews_id( Integer.parseInt(request.getParameter("news_id")) );

//두 객체가 관련성 없는 상태이니 comment안으로 news를 보유시자 
comment.setNews(news);

int result = dao.insert(comment);
System.out.println("결과: "+result);
//결과처리  
// 클라이언트는 비동기 요청 시도했기때문에 JSON으로 보낼 것 (html응답시 새로고침임)
// JSON사용이유는 시스템 중립적(리눅스든 IOS든 안드로이드든..)
if(result<1){
	out.print("{\"resultMsg\":\"등록실패\"}"); 
}else{
	out.print("{\"resultMsg\":\"등록성공\"}");
}

%>