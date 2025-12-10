<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>당신이 선택한 음식에 대한 결과 메시지</h3> 
<%= request.getAttribute("msg") %>
<%-- <%= (String)session.getAttribute("msg") %>--%>
</body>
</html>