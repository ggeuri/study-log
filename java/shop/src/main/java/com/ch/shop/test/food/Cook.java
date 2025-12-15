package com.ch.shop.test.food;

//현실의 요리사 정의 
public class Cook {
	private Pan pan; //has a 관계 
	
	public Cook(Pan pan) {
		this.pan = pan; 
//		pan = new Induction();// new 시도 자체의 문제점 
	}
	
	public void setPan(Pan pan) {
		this.pan=pan ; 
	}
	
	public void makeFood() {
		pan.boil();
		
	}
	
	

}
