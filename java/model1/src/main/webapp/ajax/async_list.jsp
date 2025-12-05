<%@page import="com.ch.model1.dto.Member2"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.ch.model1.board.repository.Member2DAO"%>
<%! Member2DAO dao = new Member2DAO(); %>
<%
//클라이언트의 비동기적 요청이 들어오면 서버는 HTML이 아닌 데이터만 보내야 한다 
List<Member2> list = dao.selectAll();

//클라이언트가 이해할수있는 데이터형식으로 응답 JSON 
// JSON은 립적 문자열이기때문에 스마트폰 각종 디바이스 이해할 수 있는 형식 데이터 
StringBuffer data = new StringBuffer();

data.append("[");  
for (int i = 0; i < list.size(); i++) {
    Member2 m = list.get(i);

    data.append("{")
        .append("\"member2_id\":").append(m.getMember2_id()).append(",")
        .append("\"id\":\"").append(m.getId()).append("\",")
        .append("\"name\":\"").append(m.getName()).append("\",")
        .append("\"email\":\"").append(m.getEmail()).append("\"")
        .append("}");

    if (i < list.size() - 1) {
        data.append(",");
    }
}
data.append("]");  // 배열 끝

out.print(data.toString());

%>