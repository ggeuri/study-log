<%@page import="java.util.List"%>
<%@page import="java.util.function.DoubleToIntFunction"%>
<%@page import="com.ch.model1.dto.Member2"%>
<%@page import="com.ch.model1.board.repository.Member2DAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! Member2DAO dao = new Member2DAO(); %>
<%

//톰캣로그에 출력, 우리경우, 이클립스 콘솔출력 

//파라미터받기 
request.setCharacterEncoding("utf-8");  //파라미터깨지지않도록 인코딩지정 

String id = request.getParameter("id");
String name =request.getParameter("name");
String email = request.getParameter("email");

System.out.println(id + name + email); 
                                                                                                
//DTO에 모으기 
Member2 dto = new Member2();
dto.setId(id);
dto.setName(name);
dto.setEmail(email);

int result = dao.insert(dto);

System.out.println(result);

//아래와같이 비동기요청에 대해 응답정보로 페이지 접속일으키는 코드 작성하면 
// 클라이언트가 지정한 url로 재접속 시도하기때문에 해당 html을 화면에 렌더링 = 새로고침효과. 즉 새로고침없는 without reloading기능 사라짐
//해결책 : 화면전체 보내지말고 순수 목록데이터만 전송해주면 클라이언트는 그 데이터를 js로 동적처리 
// response.sendRedirect("/ajax/async_regist.jsp");

//게시물 목록 가져오기 (ajax핵심) 

List<Member2> list = dao.selectAll();

StringBuffer data = new StringBuffer();

//문자열임 객체아님 
data.append("{\"name\":\"여원\",\"email\":\"ddong.com\"}");

out.print(data.toString());

%>