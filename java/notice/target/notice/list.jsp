<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%! //!붙이면 선언부 =멤버변수와 멤버메서드 정의되는 영역 
String url = "jdbc:mysql://localhost:3306/java"; 
String user = "servlet"; 
String password = "1234"; 

Connection con; 
PreparedStatement pstmt; 
ResultSet rs ; // select 수행 후 표를 담아 제어할 수 있는 객체 
%>
<!-- 가져온 소스 
https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_table_zebra
https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_contact_form

JSP는 서블릿이므로 <%%>영역(스크립틀릿)에서 개발자가 코드를 작성하면 이 jsp가 tomcat에 의해 서블릿으로 변환되어질때 
생명주기(init,service,destroy)중 service()메서드 영역에 코드를 작성한 것으로 처리 
따라서 클라이언트의 요청을 처리하는 메서드인 service()메서드에서 mysql의 데이터를 가져와 화면에 출력 
주의 _서블릿으로도 가능하지만 디자인 취약 
 -->
 
 <%Class.forName("com.mysql.cj.jdbc.Driver"); 
 
 con = DriverManager.getConnection(url,user,password); 
 
 String sql = "select * from notice";
 
 pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
 
 rs =  pstmt.executeQuery(); 
 
 %>
 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<p>For zebra-striped tables, use the nth-child() selector and add a
		background-color to all even (or odd) table rows:</p>

	<table>
		<tr>
			<th>notice_id</th>
			<th>title</th>
			<th>writer</th>
			<th>content</th>
			<th>regdate</th>
			<th>hit</th>
		</tr>
<% rs.last(); //커서를 ResultSet의 마지막으로 이동
out.print(rs.getRow()); 
	while(rs.next()){ %> 
		<tr>
			<td><%=rs.getInt(notice_id)%></td>
			<td><%=rs.getString(title)%></td>
			<td><%=rs.getString(write)r%></td>
			<td><%=rs.getString(content)%></td>
			<td><%=rs.getStrign(regdate)%></td>
			<td><%=rs.getInt(hit)%></td>
		</tr>
<%} %>
		<tr>
			<td colspan="5">
			<button onClick="location.href='/notice/regist.jsp'">등록</button>
			</td>
		</tr>
	</table>

</body>
</html>
