<%@ page contentType="text/html; charset=UTF-8"%>
<%
//JSP는 내장객체 지원. 자료형 명시안해도 이미 정해진 객체 변수명 사용 가능.  
//ex) out, request, response, session, application 

//생명력 순위 
//1. application = 어플리케이션 전역정보 가진 객체(서블릿에서 자료형 = ServletContext임. 톰캣과 생명주기 동일 서버가동시간동안 살아있음)
//2. session = 클라이언트 세션쿠키 유효한 동안, 서버에서 정해놓은 일정시간동안 재요청 없을 때까지(주로 로그인 인증에 사용)
//3. request = 젤 짧음. 요청들어와서 응답 처리될 때까지 생명 유지 

//내장객체 자료형 
//ServletContext 
//HttpSession 
//HttpServletRequest 

application.setAttribute("born", "서울");
session.setAttribute("id", "GR");
request.setAttribute("hobby", "숨쉬기");
%>
<a href="/test/result.jsp">재접속</a>