<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    // 톰캣에 JNDI로 설정해놓은 커넥션풀 사용해보기 

    // 톰캣에 설정해놓은 자원을 이름으로 검색
    InitialContext ctx = new InitialContext(); // JNDI 검색 객체 
    DataSource pool = (DataSource) ctx.lookup("java:comp/env/jndi/mysql"); 
    // java:comp/env/ 까지는 접두어

    Connection con = pool.getConnection(); 

    out.print("풀로부터 얻어온 커넥션 객체: " + con);

    con.close(); 
%>