<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- 	위의 페이지 지시 영역은 현재jsp 가 Tomcat 에 의해 서블릿으로 코딩되어질 때
	text/html 부분은 respons.setContentType();
	charset=utf-8 response.setCharacterEncoding("utf-8");
	
	select * from notice where notice_id =2; 쿼리를 수행하여 레코드를 화면에 보여주기!   -->
	
<%!// ! 를 붙이면 선언부이다. (선언부란? 이 jsp 가 서블릿으로 전환될 때 멤버변수와 멤버메서드가 정의되는 영역.  )

/*  */
	String url = "jdbc:mysql://localhost:3306/java";
	String user = "servlet";
	String pass = "1234";

	Connection con;
	PreparedStatement pstmt;
	ResultSet resultSet ; 
%>
<%
	String notice_id = request.getParameter("notice_id");

/* HTTP통신에서 주고받는 파라미터는 모두 문자열로 인식 예) 1-> "1" 
String notice_id = request.getParameter("notice_id");
request란 서블릿의 service(요청객체,응답객체) 중 HttpServletRequest 인터페이스를 가리키는 내장객체, 그러다보니 개발자가 변수명을 정한 것이 아니라 이미 jsp문법에서 정해진 이름
*/
	String sql = "select * from notice where notice_id="+notice_id;
	
	Class.forName("com.mysql.cj.jdbc.Driver"); 
	
	// 접속하기 
	con = DriverManager.getConnection(url, user, pass);
	
	pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
	
	// DML 이 아닌 select 이기 때문에 메서드는 executeQuery() 를 사용해야 한다~
	resultSet = pstmt.executeQuery();
	
	//커서는 언제나 before first 위치에 있기 때문에 한칸 이동하자 rs.next();
	
    if(resultSet.next() == false){
        // 해당 ID 게시물이 없을 때 처리 (간단히 메세지만)
        out.println("존재하지 않는 게시물입니다.");
        return;
    }
	

 %>
 
	<!DOCTYPE html>
	<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		<style>
			body {
				font-family: Arial, Helvetica, sans-serif;
			}

			* {
				box-sizing: border-box;
			}

			input[type=text],
			select,
			textarea {
				width: 100%;
				padding: 12px;
				border: 1px solid #ccc;
				border-radius: 4px;
				box-sizing: border-box;
				margin-top: 6px;
				margin-bottom: 16px;
				resize: vertical;
			}

			input[type=button] {
				background-color: coral;
				color: white;
				padding: 12px 20px;
				border: none;
				border-radius: 4px;
				cursor: pointer;
			}

			input[type=button]:hover {
				background-color: #45a049;
			}

			.container {
				border-radius: 5px;
				background-color: #f2f2f2;
				padding: 20px;
			}
		</style>
		
		<script>
			
			function del(){
				if(confirm("삭제하시겠습니까?")){
 					location.href='/notice/delete?notice_id=<%=resultSet.getInt("notice_id")%>';					
				}
			}
			
			function edit(){
		        if(confirm("수정하시겠습니까?")){
		            // 작성된 폼 양식을 서버로 전송
		            const form1 = document.getElementById("form1");  // 
		            form1.action = "/notice/edit";
		            form1.method = "post";
		            form1.submit();
		        }
			}
		</script>
	</head>

	<body>

		<h3>게시물 상세보기~</h3>

		<div class="container">
			<form id="form1">
			<!-- 파라미터 중 유저에게 노출된 필요 없는 경우, 존재는 하나 눈에 보이지 않게 하는 목적으로 사용 
			ex ) 신용카드결제시스템 등 개발 시 많이 사용됨  -->
				<input type="hidden"  name="notice_id" value="<%= resultSet.getString("notice_id") %>" style="background:yellow">
				<input type="text"  name="title" value="<%= resultSet.getString("title") %>">
				<input type="text"  name="writer" value="<%= resultSet.getString("writer") %>">
				<textarea name="content" style="height: 200px" ><%= resultSet.getString("content") %></textarea>
				<!--  textarea는왜 혼자 따로 뺴야할까? 태그와 태그 쌍으로 이루어진 아이라 사이에 넣어야함 -->
				<input type="button" value="수정" onClick="edit()">
				<input type="button" value="삭제" onClick="del()">
				
				<!-- js 에서 링크를 표현한 내장객체를 location -->
				<input type="button" value="목록" onClick="location.href='/notice/list.jsp'">
			</form>
		</div>

	</body>
	
	</html>
	
<%
	resultSet.close();
	pstmt.close();
	con.close();
%>