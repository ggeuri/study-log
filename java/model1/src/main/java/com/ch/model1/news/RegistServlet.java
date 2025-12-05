package com.ch.model1.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ch.model1.board.repository.NewsDAO;
import com.ch.model1.dto.News;


// 뉴스기사 등록요청 처리할 서블릿 
public class RegistServlet extends HttpServlet {
	NewsDAO newsDAO = new NewsDAO();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 동기방식으로 전송한 파라미터 받아서 데이터베이스(간접 - DAO)에 넣자 
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); 
		
		String title = request.getParameter("title"); 
		String writer = request.getParameter("writer");
		String content = request.getParameter("content"); 
		
		out.print(title + writer + content);
		
		//DAO에게 일시키기 
		News news = new News(); 
		
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		int result = newsDAO.insert(news);

		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		if(result <1 ) {
			sb.append("alert('인서트 실패');");
			sb.append("history.back();"); 
		}else { 
			sb.append("alert('인서트 성공');"); 
			sb.append("location.href='/news/list.jsp';");
//			response.sendRedirect("/comment/write.jsp");
		}
		sb.append("</script>");
		
		out.print(sb.toString());
	
	}

}
