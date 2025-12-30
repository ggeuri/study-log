package com.ch.shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class TopCategory {
	private int topcategory_id;
	private String topname; 
	private List<SubCategory> subList;
	

}
