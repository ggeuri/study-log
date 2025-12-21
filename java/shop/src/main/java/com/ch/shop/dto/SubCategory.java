package com.ch.shop.dto;

import lombok.Data;

@Data
public class SubCategory {
	private int subcategory_id ;  
	private String subname; 
	//부모객체 보유 OOP에서는 has a관계로 연결 (join은 숫자로 연결함)
	private TopCategory topCategory;

}
