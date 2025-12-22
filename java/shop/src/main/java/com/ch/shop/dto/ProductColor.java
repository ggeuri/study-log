package com.ch.shop.dto;

import lombok.Data;

@Data
public class ProductColor {
	private int product_color_id;
	private Product product; //OOP에서는 부모는 숫자가 아닌 객체 
	private Color color; 
}
