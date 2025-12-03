package com.ch.model1.singleton;

public class UsdDog {
	
	public static void main(String[] args) {
		
		//강아지 클래스가현재 생성자를 막아놓았음 -> new 하지말고 강아지가 제공하는 
		// 클래스메서드인 static메서드 통해 강아지 인스턴스 얻어오기 
		
		Dog d1 = Dog.getInstance();
		System.out.println("d1 = " + d1);
		
		d1.bark();
	
		Dog d2 = Dog.getInstance();
		System.out.println("d2 = " + d2);
		 
	
	}

}
