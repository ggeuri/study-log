<%@page import="com.ch.mybatisapp.dto.News"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.ch.mybatisapp.mybatis.MybatisConfig"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
MybatisConfig mybatisConfig = MybatisConfig.getInstance();

SqlSession sqlSession = mybatisConfig.getSqlSession();

News news = new News(); 
news.setTitle("냐냐냐냔");
news.setWriter("새날");
news.setContent("황갈");

int result = sqlSession.insert("com.ch.mybatisapp.dto.News.insert", news);

//마이바티스의 Sqlsession은 DML수행시 트랜젝션 commit해야함 
sqlSession.commit();


if(result<1){
	out.print("등록실패");
}else{
	out.print("등록성공");}

mybatisConfig.release(sqlSession); 

%>
