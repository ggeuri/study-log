package com.ch.site1118.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*javaEE 기술이 서블릿 기반이므로 디자인 결과물까지 out.print() 문자열로 처리 
 * 따라서 웹페이지의 양이 많아지거나 디자인 코드량이 많아지면 유지보수성이 현저히 떨어짐 
 * 즉 디자인 표현에 취약함 */

public class ListController extends HttpServlet{
//	웹 브라우저로 요청들어올때 클라이언트가 GET방식으로 들어올 경우 이 메서드가동작함 
//	http 통신에 의하면 클라는 서버에 요청을 시도할 때, 그 목적에 맞는 메서드를 선택하게 되어있음 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		클라이언트인 브라우저가 보게될 컨텐츠작성 
//		다국어 안깨지려면 인코딩 필요 
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
//		5층의 3호수 
//		String tag = "<table border = \"2px\">" ;
//		for(int i=5; i>=1; i--) {
//			tag += "<tr>"; // 건축물에서 층수는 바깥for 
//			for(int y=1; y<=3; y++) {
//				tag += "<td>"+i+"0"+y+"호</td>"; // 건축물에서 호수는 안				
//			}
//			tag += "</tr>";			
//		}
//		tag += "</table>";
		
		
//		스트링 불변이니까 이렇게 짜지말고 StringBuffer(동기화 지원X) StringBuilder(동기화지원)을 사용해야함 
		StringBuffer sb = new StringBuffer();
		
		sb.append("<table border = \"2px\">" );
		for(int i=5; i>=1; i--) {
			sb.append( "<tr>"); // 건축물에서 층수는 바깥for 
			for(int y=1; y<=3; y++) {
				sb.append("<td>"+i+"0"+y+"호</td>"); // 건축물에서 호수는 안				
			}
			sb.append("</tr>");			
		}
		sb.append("</table>");
		
		out.print(sb.toString());
	}

}
