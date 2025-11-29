
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%!// ! 를 붙이면 선언부이다. (선언부란? 이 jsp 가 서블릿으로 전환될 때 멤버변수와 멤버메서드가 정의되는 영역.  )
	String url = "jdbc:mysql://localhost:3306/java";
	String user = "servlet";
	String pass = "1234";

	Connection con;
	PreparedStatement pstmt;// 쿼리문 수행객체 
	ResultSet resultSet ; // select 수행 수 표를 담아 제어할 수 있는 객체 
%>
<%
	// JSP 는 서블릿이므로, 이 영역(스크립틀릿) 에서 개발자가 코드를 작성하면, 이 JSP 가 Tomcat에 의해 
	// 서블릿으로 변환될 때 생명주기(init, service, distroy)중 service()메서드 영역에 코드를 작성 한 것으로 본다. 
	// 따라서 클라이언트의 요청을 처리하는 메서드인 service() 메서드에서  mysql의 데이터를 가져와 화면에 출력~!
	// 주의) 서블릿으로도 가능은 하지만, 수많은 코드 라인마다 out.print() 출력해야 하므로 디자인 작업시 유지보수성 및 효율성이 떨어짐! 
	
	Class.forName("com.mysql.cj.jdbc.Driver"); // Mysql 드라이버 넣자!
	
	// 접속하기 
	con = DriverManager.getConnection(url, user, pass);
	String sql = "select * from notice";
	
	/* 
		TYPE_SCROLL_INSENSITIVE -> 스크롤 가능한 옵션
		CONCUR_READ_ONLY -> 오직 읽기 전용으로만!
	*/ 
	pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // 쿼리문 수행객체 생성
	
	// DML 이 아닌 select 이기 때문에 메서드는 executeQuery() 를 사용해야 한다~
	resultSet = pstmt.executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
a{text-decoration:none}
</style>
</head>
<body>

	<h2>Zebra Striped Table</h2>
	<p>For zebra-striped tables, use the nth-child() selector and add a
		background-color to all even (or odd) table rows:</p>

	<table>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%
		  resultSet.last(); // 커서를 제일 마지막 행으로 이동. 
		  out.print("현재 테이블의 총 레코드 수는" + resultSet.getRow());
		  int lastIdx = resultSet.getRow();
		  // rs 의 기본 속성은 ResultSet.TYPE_FORWARD_ONLY 로 되어있다.
		  // TYPE_FORWARD_ONLY 상수로 지정되면, 커서가 오직 전방향으로 한칸씩만 이동 가능하다! 
		  // PrepareStatement 생성 시 상수를 지정해야 한다. TYPE_SCROLL_INSENSITIVE
		  
		  resultSet.beforeFirst(); // 커서 초기화 ~
		%>
		<%
		while (resultSet.next()){ // 레코드 수 만큼 - next() 메서드가 true 를 반환하는동안
		%>
		
		<tr>
			<td><%= resultSet.getRow() %></td>
			<td><a href="/notice/detail.jsp?notice_id=<%=resultSet.getInt("notice_id")%>"><%= resultSet.getString("title")%></a></td>
			<td><%= resultSet.getString("writer")%></td>
			<td><%= resultSet.getString("regdate")%></td>
			<td><%= resultSet.getInt("hit")%></td>
		</tr>
		<%
		}
		%>
		<tr>
			<td colspan="5" style="text-align: right">
				<button type="button" onClick="location.href='/notice/regist.jsp'">글쓰기</button>
			</td>
		</tr>
	</table>



</body>
</html>

<%
	resultSet.close();
	pstmt.close();
	con.close();
%>
