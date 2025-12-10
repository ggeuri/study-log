package com.ch.mvcframework.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//엔터프라이즈급의 규모가 큰 애플리케이션에서 클라이언트의 수많은 요청마다 1:1대응하는 서블릿을 선언 
// 매핑한다면, 매핑규모가 너무나 방대해지고 유지보수성이 오히려 떨어짐 요청매핑은 오직 하나의 진입점으로 몰아서 관리 

public class DispatcherServlet extends HttpServlet {
	//결국 if문을 커멘드 패턴과 팩토리 패턴 이용하여 대체하기 위한 준비물들 
	FileInputStream fis; 
	Properties props; 
	
	//아래의 init은 서블릿이 인스턴스가 생성되어진 직후 호출되는 서블릿 초기화 목적 메서드
	//init() 메서드 안에 명시된 매개변수는 서블릿과 관련된 환경정보를 갖고있는 객체다 ! 
	public void init(ServletConfig config) {
		try {
			//서블릿 환경정보를 가진 객체(ServletConfig) 이용해서 애플리케이션정보 가진 객체(ServletContext) 가져옴 
			ServletContext application = config.getServletContext();
			
			//현재 웹애플리케이션이 이클립스 내부 톰캣으로 실행될지, 아니면 실제 서버에서 실행될지 개발자가 알필요 없이 현재 애플리케이션 기준으로 파일명 명시
			// 알아서 경로 반환 ServletContext.getRealPath! 
			String paramValue = config.getInitParameter("contextConfigLocation"); 
			String realPath = application.getRealPath(paramValue);
			System.out.println(realPath);
			System.out.println(paramValue);
			
			
			fis = new FileInputStream(realPath);
			props = new Properties();
			props.load(fis);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //추후 필터단계로 올릴예정 
		//클라이언트 요청방식 다양하니 아래의 메서드 하나로 몰아놓으면 코드는 메서드마다 작성할 필요없음 
		System.out.println("클라이언트의 요청을 감지하였습니다 ! ");

		// 음식이든, 영화든 .. FoodManager? MovieManager? 걍 Manager ! 가 요청유형뭔지부터 파악 필요
		// 요청시 사용한 주소표현식 URI 클라이언트가 원하는게 무엇인지에 대한 구분값임 

		String uri = request.getRequestURI();
		
		
		// if날리고 커멘드패턴으로 ..요청많을때 if문처리하면 힘드렁 
		// 각 요청을 객체로 처리 = GOF는 커멘드패턴이라 해씀 + 여기에 팩토리패턴도 같이  
		// 팩토리패턴은 객체 생성 방법 감춤.개발자가 객체 인스턴스 얻어갈 수 있게 하는 클래스 정의기법(싱글톤처럼?)
		
//		if(uri.equals("/movie.do")){
//			//영화에게 전달
////			MovieController controller = new MovieController();
////			controller.execute(request, response);
//			String controllerPath = props.getProperty(uri);
//			System.out.println("영화" + controllerPath);
//			
//		}else if(uri.equals("/food.do")){
//			//음식에게 전달 
////			FoodController controller = new FoodController();
////			controller.handle(request, response);
//		 
//		}
		String controllerPath = props.getProperty(uri);
		System.out.println(uri +"에 동작할 하위 전문 컨트롤러는" + controllerPath);
		
		try {
			//동적으로클래스가로드됨. static==method 영역 
			//클래스에 대한 정보를 가진 클래스. 현재 이클래스가 보유한 메서드명, 생성자, 속성등 
			Class clazz = Class.forName(controllerPath); // static영역에 동적으로 클래스 코드 올리기 
			Object obj = clazz.getConstructor().newInstance();//static 영역에 올라온 클래스 원본 코드 대상으로 인스턴스 1개 생성 
																  // new연산자만이 인스턴스를 만들수있는 것은 아니다.
			Controller controller = (Controller)obj;
			//현 시점 객체가 Movie인지, Food인지 모르니까 최상위객체 Controller만들어서 형변환 
			
			controller.execute(request, response);//자식이 부모 메서드 오버라이드했으니 자식메서드 호출(다형성)
			
			//viewName에는 실제 jsp가 아니라 검색키만 있으니까 
			// 디스패처서블릿이 다시 검색( props.getProperty)하여 실제 jsp파일 얻어 응답 
			String viewName = controller.getViewName();
			String viewPage = props.getProperty(viewName);
			
			if(controller.isFoward()) {
				RequestDispatcher dis = request.getRequestDispatcher(viewPage);
				dis.forward(request, response);

			} else {
				response.sendRedirect(viewPage);//클라이언트로하여금 재접속할것을 응답정보에 추가함 + 하위컨트롤러가 포워딩 요청했으면 포워딩 				
				
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//음식,영화,블로그,음악 등등의 모든 요청을 이 클래스에서 받아야함 Get, Post, Put, Delete.. 다 
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	//서블릿의 생명주기 메서드중 서블릿이 소멸할 때 호출되는 메서드인 destroy()재정의
	//반드시 닫아야할 자원들을 해제할 때 중요하게 사용
	
	public void destory() {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
