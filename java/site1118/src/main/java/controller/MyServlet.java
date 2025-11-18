package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*클래스 중 오직 javaEE의 서버에서만 해석 / 실행되어질 수 있는 클래스를가리켜 서블릿이라한다 
 * 현재클래스를 서블릿으로 만들려면 HttpServlet을 상속받으면 된다. */
public class MyServlet extends HttpServlet{
/*extends 는 is a 관계다. 자바에서 상속관계는 is a 관계로, 같은 자료형으로 간주 */
	
	
//	서블릿은 init이 초기화. 이 서블릿이 컨테이너에 의해 최초로 인스턴스가 생성될때 초기화를 위해 무조건 호출되는 메서드 
//	생성자 아님.일반메서드라 생성자 호출 직후에 초기화 위해 이른 시점에 호출되는 것임 [이건 누군가 처음 서버 접속할 때, 리퀘스트 리스폰스 메모리 생성. 만들어짐 ]
//	서블릿 생명주기 3가지 메서드 - init(), service(), destory() 
//	서블릿의 생성은 컨테이너(고양이 서버)가 담당하며, 이 서블릿의 초기화 정보(ServletConfig)를 넘겨줌 
//	방문 - 쌍방울 - 스레드 만들어짐 - new - init(응애) 호출 - 스레드가 일하는메서드(service) 호출(얘네는 쌍방울 필요로함. 매개변수로 쌍방울(요청객체,응답객체))  
//	호출된 서비스 메서드는 클라이언트가 get이냐 post냐 put이 등등에 따라 분기를 일으킴 if get -> doget / if post  
//	-> dopost 호출 [실제로 업무처리는 doXXX 메서드가 일함] - 요청이 끝나면 컨테이너(고양이서버)가 HttpServletResponse(파란구슬) 찾아서 html 짜서 바디에 넣음 -> 응답하고 response,request 스레드지워버림 
//	 tomcat끌때, Servlet 인스턴스가 소멸될 때 호출  (destroy)

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("방금 태어나서 초기화-init");
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//현재 클래스를 웹브라우저로 요청하는 클라이언트에게 메시지 출력 
		resp.setContentType("UTF-8"); // 한글쓰려면 

		PrintWriter out = resp.getWriter(); // 문자 기반의 출력 스트림 얻기 
		out.println("<h1>서블릿 테스트 성공!</h1>");
		// 개발자가 이 출력스트림에 문자열 저장해두면 고양이 서버가 알아서 웹브라우저 출력
		

	}
	
}
