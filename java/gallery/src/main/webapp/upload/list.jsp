<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<%!
/* 선언부는 서블릿으로 변환될 때 자동으로 멤버 영역 자리잡음 */
Connection con;
PreparedStatement pstmt; 
ResultSet rs;

String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
String user = "servlet"; 
String pass = "1234";

/* jsp는 사실상 서블릿
 결론 : jsp의 개발목적 : 서블릿 불편하니까 시스템인 TOMCAT(컨테이너)가 
 대신 작성해주기 위한 스크립트언어 --> */

int x = 7; 
public int getX(){
  return x; 
}
%>

<%
/*페이지 지식 영역에서 contentType()명시한 것은 이jsp가 서블릿으로 변환되어질때 
 response 객체의 매서드 중 setContentType("text/html;charset=utf-8") */

/* 오라클 연동하기  */

/* 아래의 코드는 원래 순수java 클래스에서 작성할 경우 예외처리가 강제되지만 
 현재 우리의 jsp영역은 실행직전 tomcat에 의해 서블릿으로 변환되어지며 특히 스크립틀릿 영역은 
 service()매서드로코드가 작성되고 이때 tomcat이 예외처리까지 해버렸으므로, jsp에서는 예외처리를 강제하지 않음 */

/* 1단계 드라이버 로드  */
Class.forName("oracle.jdbc.driver.OracleDriver");

/* 2단계 Connection 연결 */
con = DriverManager.getConnection(url, user, pass);

/* 3단계 쿼리실행 */
String sql = "select * from gallery"; 
pstmt = con.prepareStatement(sql); //쿼리 수행 객체 생성 
rs = pstmt.executeQuery();

/*  선언한 적도 없는 레퍼런스를 변수로 사용 ? 
-> jsp는 총 9가지정도의 내장객체를 지원(빌트인오브젝트)  
- 문자 기반의 출력스트림 객체를 미리 변수명까지 지정해놓음. out 
이 영역을 스크립틀릿이라 하며 추후 고양이가 jsp가 서블릿으로 변환되어질때 
이 영역에 작성한 코드는 service안에 작성한 것과 같아진다 */
out.print(getX());
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
<pre>
  JSP란 Java Server Page (자바기반서버에서실행되는 페이지)
  오직 java기반 서버에서만 해석 및 실행 
  장점 - 서블릿과 달리 HTML 혼용 사용 가능함 (서블릿 디자인 취약점 보완 )

  2) jsp의 코드 
  - jsp는 다음 3가지 영역에 코드작성
   1. 지시영역- @붙은 영역
   현재 jsp 페이지의 인코딩, 파일 유형, 다른 클래스 import 등을 위한 영역 
   2. 선언부 !붙은 영역 
   멤버영역 (멤버변수나 메서드를 선언할 수 있는 영역)
   3. 스크립틀릿 영역 - 실행영역
   실질적으로 로직 작성하게될 영역  
</pre>

<h2>Zebra Striped Table</h2>
<p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table rows:</p>

<table>
  <tr>
    <th>gallery_id</th>
    <th>title</th>
    <th>filename</th>
  </tr>

  <!--  rs 객체의 next()매서드를 호출할 때마다 커서가 밑으로 한칸씩 전진 . 
        이때 커서가 위치한 행의 레코드가 존재할 경우 true, 존재하지않으면 false
        따라서 모든 레코드만큼 반복문 수행하려면 next()참인동안반복 -->
  <% while(rs.next()){ %>
    <tr>
      <td><% out.print(rs.getInt("gallery_id")); %></td>
      <td><% out.print(rs.getString("title")); %></td>
      <td><% out.print(rs.getString("filename")); %></td>
    </tr>
  <% } %>  
</table>

</body>
</html>

<%
/* 사용 완료된 자원 반환 */
rs.close();
pstmt.close();
con.close();
%>