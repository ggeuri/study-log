<%@page import="com.ch.model1.dto.Member2"%>
<%@page import="com.ch.model1.board.repository.Member2DAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! 
Member2DAO dao = new Member2DAO();
%>

<%
//스크립틀릿 (이 jsp가 서블릿으로 변환될 때, service(request,response)메서드 영역)
// 넘어온 파라미터를 받아서 mysql의 member2테이블에 인서트하자 

//jsp에서는 개발자가 요청객체, 응답객체를 별도로 변수명 바꿀수 없음 .
// 이유 : 이미 결정되어있음 (내장객체. 빌트인오브젝트)
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
String name = request.getParameter("name");
String email = request.getParameter("email");

Member2 dto = new Member2();

dto.setId(id);
dto.setName(name);
dto.setEmail(email);

int result = dao.insert(dto);
%>
<script>
<%if(result<1){%>
out.print("성");
history.back(); 
<%}else{%>
location.href="/ajax/main.jsp";
<%} %>
</script>