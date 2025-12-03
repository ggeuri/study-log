<%@page import="java.util.ArrayList"%>
<%@page import="com.ch.model1.dto.Board"%>
<%@page import="java.util.List"%>
<%@page import="com.ch.model1.board.repository.BoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! 
BoardDAO boarDao = new BoardDAO(); 
List<Board> list = new ArrayList();
%>
<% // 이 영역은 이 jsp파일이 서블릿으로 변환될때 이 jsp파일이 서블릿으로 변환될 때 service() 영역이므로 DB연동가능하지만 하면안됨 
//디자인 - DB하나로 합쳐지면...........추후 DB연동코드 재사용 불가 보안은누가하냐 
list = boarDao.selectAll();
out.print("등록된 게시물 수는 " + list.size());

int totalRecord=list.size();
int pageSize = 10 ; 
int totalPage = (int)Math.ceil((float)totalRecord/pageSize); 
int blockSize=10; 
int currentPage = 1; 
if(request.getParameter("currentPage")!=null){
	currentPage = Integer.parseInt(request.getParameter("currentPage")); 
}
int firstPage = currentPage - (currentPage-1)%blockSize;
int lastPage = firstPage+(blockSize-1);
int curPos = (currentPage-1)*pageSize; //페이지당 List의 시작인덱스 현재 페이지와 비례하여 10씩 증가 (10은 pageSize )
int num = totalRecord - curPos;

if (currentPage < 1) currentPage = 1;
if (currentPage > totalPage) currentPage = totalPage;
if (lastPage > totalPage) {
    lastPage = totalPage;
}


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

<h2>Zebra Striped Table</h2>
<p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table rows:</p>

<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  <%
  //rs에 들어있는 레코드 한칸씩 이동하면서 꺼내자 
  
for(int i = 0 ; i < pageSize ; i++ ){
	if(num < 1) break; 
	Board board = list.get(curPos++);
  %>
  <tr>
    <td><%= num-- %></td>
    <td><a href="/board/detail.jsp?board_id=<%=board.getBoard_id()%>"><%= board.getTitle() %></a></td>
    <td><%= board.getWriter() %></td>
    <td><%= board.getRegdate() %></td>
    <td><%= board.getHit() %></td>
 </tr>
  <%} %>
<tr>
	<td > <button onClick="location.href='/board/write.jsp';">글 등록</button></td>
	<td colspan="4"><%for (int i = firstPage ; i<=lastPage;i++){ %>
	<a href="/board/list.jsp?currentPage=<%=i%>">[<%=i%>]</a>
	<%} %>
	</td>
</tr>
</table>
</body>
</html>
