<%@page import="java.lang.reflect.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
홈페이지 메인이라 치자 ~ 
<pre>
	jsp에서는 필수적으로 사용되는 javaEE기반의 객체들을 미리메모리에 올려놓고 이름까지 지정 
	이러한 시스템에 의해 결정된 내장 객체를 가리켜 JSP내장객체라함
	따라서 변수명 바꾸거나 할수없음 
	지금은 회원정보꺼내기위해 HttpSession 자료형에 들어있는 member 꺼내야하는데 JSP에서는 HttpSession 자료형에 대한 내장객체로 session이라는 내장객체 지원 
</pre>

<%Member  member = (Member)session.getAttribute("member");
out.print(member.getName() +"님 반갑습니다." );%> 
</body>
</html>