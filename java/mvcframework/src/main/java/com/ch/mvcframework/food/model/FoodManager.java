package com.ch.mvcframework.food.model;

public class FoodManager {

	public String getAdvice(String food) {
		String msg = "선택한 음식 없음"; 
		if(food !=null){
			if(food.equals("보쌈")){
				msg = "메인"; 
			}else if (food.equals("무생채")){
				msg = "밑반찬 1"; 
			}else if (food.equals("백김치")){
				msg = "밑반찬 2"; 
			}
		}
		return msg; 
	}
	

}
