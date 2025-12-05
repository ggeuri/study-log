<%@page import="com.ch.model1.util.PagingUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ch.model1.dto.News"%>
<%@page import="java.util.List"%>
<%@page import="com.ch.model1.board.repository.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
//목록가져오기
NewsDAO newsDAO = new NewsDAO();
PagingUtil pgUtil = new PagingUtil(); 
%>
<%
List<News> newsList = newsDAO.selectAll();
pgUtil.init(newsList, request);

out.print("총레코드수"+pgUtil.getTotalRecord());
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset=utf-8>
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
</head>
<body>

<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
<%
int curPos = pgUtil.getCurPos();//페이지당 시작리스트내의인덱 
int num = pgUtil.getNum(); //페이지당 시작번호(언제나 1이상 )

%>
<% for(int i = 1 ; i <= pgUtil.getPageSize(); i++) {%>
<% 
if(num<1)break;
News news = newsList.get(curPos++); 
%>
  <tr>
    <td><%= num-- %></td>
    <td><a href="/news/content.jsp?news_id=<%=news.getNews_id()%>"><%= news.getTitle()%></a></td>
    <td><%= news.getWriter() %></td>
    <td><%= news.getRegdate() %></td>
    <td><%= news.getHit() %></td>
 </tr>
<%} %>

<tr>
	<td > <button onClick="location.href='/news/write.jsp';">글 등록</button></td>
	<td colspan="4">
	</td>
</tr>
</table>
</body>
</html>
