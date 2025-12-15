package com.ch.shop.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch.shop.dto.Board;

//기존 MVC프레임워크에서는 모든 요청마다 1:1대응 컨트롤러 매핑이었으나 스프링 MVC는 게시판 1개에 대한 목록,쓰기,상세보기,수정,삭제 하나의 컨트롤러로 가능 
// 클래스기반이 아니라 메서드 기반이라서 ! 
@Controller
public class BoardController {
	//글쓰기 폼 요청 -jsp가 WEB-INF 하위로 갔기 때문에 브라우저에서 직접 접근 불가  -> 따라서 메서드에서 /board/write.jsp매핑 
	@RequestMapping("/board/registform")
	public ModelAndView registForm() {
		//일시킬게없음 
		//4단계도없음 
		//DispatcherServlet에게 완전한 jsp경로를 반환하게 되면, 파일명이 바뀔때, 이 클래스도 영향을 받으므로
		//무언가 jsp를 대신할만한 키 등을 구상해야 하는데, 스프링의 창시자인 로드 존슨은 접두어, 접미어를 활용하는 방식을 고안해냄
		//따라서 개발자는 전체 파일명 경로 중 변하지 않는다고 생각하는 부분에 대해 접두어, 접미어를 규칙으로 정하여 알맹이만 반환하는
		//방법을 쓰면 된다..이때 하위컨트롤러가 DispatcherServlet에게 정보를 반환할때는 String형으로 반환해도 되지만,
		//ModelAndView라는 객체를 이용할 수도 있다..
		//참고로 ModelAndView에는 데이터를 담을때는 Model객체에 자동으로 담기고, jsp접두어, 접미어를 제외한 문자열을 넣어둘때는
		//View 객체에 담기는데, ModelAndView는 이 두객체를 합쳐놓은 객체임
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/write");
		return mav; 
	}
	
	
	//글 목록 페이지 요청 처리 
	//뭐가들어오면 이걸 호출할거다 
	@RequestMapping("/board/list")
	public ModelAndView getList() {
		//3단계 수행 
		System.out.println("클라이언트의 목록 요청 감지");
		
		//4단계 저장
		return null ; 
	}
	
	//글쓰기 요청처리 
	//글쓰기 요청 처리
	//메서드의 매개변수에 VO(Value Object)로 받을 경우
	//스프링에서 자체적으로 자동 매핑에 의해 파라미터값들을 채워넣는다
	//단, 전제 조건? 파라미터명과 VO의 변수명이 반드시 일치해야 한다
	//DTO와 VO는 비슷. DTO는 테이블 반영객체라 클라이언트에 노출되지 않는 것이 좋음.
	//파라미터 받기만 하면되면 VO사용 
	
	@RequestMapping("/board/regist")
	public ModelAndView regist(Board board) {
		System.out.println(board.getTitle());
		System.out.println(board.getWriter());
		System.out.println(board.getContent());
	
		return null; 
	}
	
	
	
	
	//글 상세보기 요청 처리
	
	//글 수정 
	
	//글 삭제 

}
