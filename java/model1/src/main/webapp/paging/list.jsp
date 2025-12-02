<%@ page contentType="text/html; charset=UTF-8"%>
<%
//하나의 페이지에 많은양의 데이터가 출력되면 스크롤 발생. 한페이지당 보여질 레코드수 제한 가하고 
//나머지 데이터에 대해서 여러페이지 링크지원해주려면 총 게시물수에대해 산수계산 요구됨 

//기본전제조건 총 레코드수 

int totalRecord = 26; 
//페이지당 보여질 레코드수 
int pageSize = 10; 
int totalPage = totalRecord / pageSize;    
int blockSize=10;
int currentPage = 1; 
if(request.getParameter("currentPage") != null){
	currentPage = Integer.parseInt(request.getParameter("currentPage")); //현재 유저가 보고 있는 페이지,이 값은 클라이언트의 get방식으로 전송된 파라미터로 대체 	
}

if (totalRecord % pageSize != 0) {             
    totalPage++;                              
}

int firstPage ; 
int lastPage ; 
int num; //페이지당 시작번호 예 ) 1page일때믄 26부터 차감, 2page일때는 15부터 차감.. 3page일때는 6부터차감 

num = totalRecord-(blockSize*(currentPage-1)); 


if (currentPage < 1) currentPage = 1;
if (currentPage > totalPage) currentPage = totalPage;

firstPage = ((currentPage - 1) / blockSize) * blockSize + 1;
lastPage  = firstPage + blockSize - 1;
if (lastPage > totalPage) {
    lastPage = totalPage;
}

//자바스럽게할거면 (int)Math.ceil((float)totalRecord/pageSize)

%>
<%="totalRecord "+totalRecord+"<br>" %>
<%="pageSize "+pageSize+"<br>" %>
<%="totalPage "+totalPage+"<br>" %>
<%="현재 당신이 보고있는 currentPage "+currentPage+"<br>" %>
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

a{text-decoration:none;}

/* 유저가 현재 보고있는 페이지에 대한 시각적 효과  */
.numStyle{
font-size:22px;
font-weight : bold; 
background:yellow;
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
  <%for(int i = 1; i <= pageSize; i++){ if(num <1) break; %>
  <tr>
    <td><%=num--%></td>
    <td>2</td>
    <td>3</td>
    <td>4</td>
    <td>5</td>
 </tr>
 <%} %>
 <tr>
 <td colspan="5" align="center">
 <a href="/paging/list.jsp?currentPage=<%=firstPage-1%>">≤</a>
  <%for(int i = firstPage; i <= lastPage; i++){ %>
  <a <% if(i==currentPage){%> class = "numStyle"<%} %> href="/paging/list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
 <%} %>
 <a href="/paging/list.jsp?currentPage=<%=lastPage+1%>">≥</a>
 </td>
 </tr>
</table>

</body>
</html>
