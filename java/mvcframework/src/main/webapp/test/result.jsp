<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>생명력배틀</h3> 
<%= application.getAttribute("born") %>
<%= session.getAttribute("id") %>
<%= request.getAttribute("hobby") %>
<%
//현재 웹애플리케이션 내 자원의 실제 OS상의 경로 반환해줌 리눅스든,맥이든,윈도우든 
String path = application.getRealPath("WEB-INF/servlet-mapping.txt");
out.print(path);

%>


<!-- 세션 내장객체의 자료형 HttpSession 
HttpServletRequest 
ServletContext -->
</body>
</html>