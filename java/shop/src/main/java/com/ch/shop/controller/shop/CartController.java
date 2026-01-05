package com.ch.shop.controller.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch.shop.dto.Cart;
import com.ch.shop.dto.Member;

@Controller
public class CartController {
	
	@GetMapping("/cart/main")
	public String getMain(HttpSession session) {
		String viewName ="";
		//로그인 세션 체크 (세션에 멤버있냐없냐) 
		Member member = (Member) session.getAttribute("member");
		if(member==null) {
			viewName = "shop/member/login";
		}else {
			viewName = "shop/cart/list";
		}
		return viewName; 
	}

	/*
	[장바구니는 임시 저장] → 구현 선택지 3가지

	1) Session(메모리)
	- 장점: DB 불필요, 세션 만료 시 자동 정리(개발자가 별도 삭제 로직 불필요)
	- 단점: 분산환경(서버 여러 대)에서 세션 공유 어려움, 사용자 많으면 메모리 사용 증가
	- 용도: 소규모/테스트/연구에 적합, 실서버 운영에는 보통 비권장

	2) DB 저장(RDBMS)
	- 장점: 원하는 기간 동안 제한 없이 저장 가능(영속성)
	- 단점: 주문 완료/만료 시 삭제(정리) 로직을 개발자가 직접 구현해야 함
	        사용자 많으면 DB 용량이 계속 커질 수 있음

	3) Redis(인메모리 DB)
	- 특징: RDBMS처럼 테이블/컬럼 스키마가 없고, Key-Value/Map 구조로 저장
	- 장점: 메모리 기반이라 매우 빠름(캐시/세션/장바구니 같은 임시 데이터에 적합)
	- 단점: 메모리 용량을 많이 차지
	- 데이터의 유효기간(TTL)을 명시할 수 있어, 개발자가 별도 삭제 작업을 하지 않아도 됨(마침 쿠키처럼)
	*/
	
	@GetMapping("/cart/add")
	public String addCart(@RequestParam(defaultValue ="0") int product_id, HttpSession session) {
		
		//클라이언트가 전송한 상품의 product_id, 개수 이용해 Cart생성 - 보관 
		Cart cart = new Cart();
		cart.setProduct_id(product_id);
		cart.setEa(product_id);//받을 예정 
		cart.setProduct_name(null); 
		cart.setFilename(null);
		cart.setPrice(product_id);
		
		Map<Integer, Cart> map = new HashMap<Integer, Cart>();
		session.setAttribute("cart", map);
		
		return null; 
	}
}
