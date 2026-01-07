package com.ch.shop.controller.shop;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Cart;
import com.ch.shop.dto.Member;
import com.ch.shop.dto.ResponseMessage;
import com.ch.shop.exception.CartException;
import com.ch.shop.model.order.CartService;

import lombok.extern.slf4j.Slf4j;

//속도빠른 메모리기반 데이터베이인 레디스 기반의 장바구니 요청을 처리하는 컨트롤러
@Controller
@Slf4j
public class RedisCartController {
	
	@Autowired
	private CartService cartService;
	
	//장바구니 등록 비동기요청을 처리하기 위한 매핑 
	@PostMapping("/cart/regist")
	@ResponseBody
	public ResponseEntity<ResponseMessage> regist(HttpSession session, Cart cart){
		Member member = (Member) session.getAttribute("member");
		cart.setMember_id(member.getMember_id()); //누구의 장바구니 
		//Redis에 저장할 형식 중 cart:member_id product_id ea 중 key에 들어갈 값 
		log.debug("member_id={}",cart.getMember_id());
		log.debug("product_id={}",cart.getProduct_id());
		log.debug("product_name={}",cart.getProduct_name());
		log.debug("ea={}",cart.getEa());
		
		cartService.regist(cart);
		
		ResponseMessage message = new ResponseMessage();
		message.setMsg("장바구니 등록성공");
		
		return ResponseEntity.ok(message); 
	}
	
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ResponseMessage> handle(CartException e){
		
		ResponseMessage message = new ResponseMessage();
		message.setMsg("장바구니 등록실패");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message); 
	}
	
}
