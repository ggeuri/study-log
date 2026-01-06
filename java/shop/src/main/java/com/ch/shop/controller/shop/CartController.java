package com.ch.shop.controller.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Cart;
import com.ch.shop.dto.Member;
import com.ch.shop.dto.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CartController {
	
	@GetMapping("/cart/main")
	public String getMain(HttpSession session,Model model) {
		//세션에 들어있는 cart라는 key를 갖는 객체들을 List형태로 바꿔서 jsp까지 전달
		
		Map<Integer, Cart> cart = (Map)session.getAttribute("cart");
	
		List<Cart> cartList = new ArrayList<Cart>();
		for(Map.Entry<Integer, Cart> entry : cart.entrySet()) {
			log.debug("키는{},값은{}",entry.getKey() ,entry.getValue());
			cartList.add(entry.getValue());
		}                                                           
		model.addAttribute("cartList",cartList); //결과저장 !jsp로보내주려면model필요 
		
		return "shop/cart/list";
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
	
	@PostMapping("/cart/add")
	@ResponseBody
	public ResponseEntity<ResponseMessage> addCart(Cart cart, HttpSession session) {
		//세션에서 꺼내서 cartDTO에 아주자 member_id (보안을위해)
		
		//클라이언트가 전송한 상품의 product_id, 개수 이용해 Cart생성 - 보관 
		Member member = (Member) session.getAttribute("member");
		log.debug("흠흠{}",member.getMember_id());
		log.debug("product_id={}",cart.getProduct_id());
		log.debug("product_name={}",cart.getProduct_name());
		log.debug("price={}",cart.getPrice());
		log.debug("Ea={}",cart.getEa());
		
		cart.setMember_id(member.getMember_id());
		
		
		Map<Integer, Cart> map = (Map<Integer, Cart>) session.getAttribute("cart"); 
		
		if(map == null) map = new HashMap<Integer, Cart>(); 
		
		Cart old = map.get(cart.getProduct_id());
	    if (old == null) map.put(cart.getProduct_id(), cart);
	    else old.setEa(old.getEa() + cart.getEa());
	                                                                            
		session.setAttribute("cart", map);
		
		ResponseMessage msg = new ResponseMessage();
		msg.setMsg("장바구니에 상품이 담겼습니다.");
		//이 시점에 jackson 라이브러리를 직접사용하는 것이 아니라 ResponseBody에 의해 내부적으로 작동함. 
		//스프링에서 지원하는 HTTP 응답전용객체(head+body 구성) 
		
		
		return ResponseEntity.ok(msg); 
	}
}
