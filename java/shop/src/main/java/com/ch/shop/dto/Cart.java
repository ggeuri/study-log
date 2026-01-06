package com.ch.shop.dto;

import lombok.Data;

//장바구니에 담겨질 하나의 상품 정보 담을 객체 
@Data
public class Cart {
//		장바구니에 출력할 내용 담을 DTO 
		private int member_id; //누가 
		private int product_id; // 무엇을(어떤 상품을) 
		private String product_name;
		private int price;
		private int ea; // 몇개나 
	}
	