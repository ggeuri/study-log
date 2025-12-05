<%@ page contentType="text/html; charset=UTF-8"%>
<% 
//클라이언트가 비동기적으로 요청 시도, 파라미터 받고 DB에 넣은 후 응답정보는 데이터 
//JSON으로 보내줘야댐 

request.setCharacterEncoding("utf-8");
String msg = request.getParameter("msg");
String reader = request.getParameter("reader");
System.out.print(msg + reader);


%>