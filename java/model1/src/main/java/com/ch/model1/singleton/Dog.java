package com.ch.model1.singleton;

// 전세계 개발자들의 공통적 코드 패턴마다 이름 붙여서 저서를 한 책의 이름 : 디자인패턴 
// 이 책 출간된 이후부터 개발시 패턴이름제시하면 개발자들간 업무소통 원활해짐 

// 하나의 클래스로부터 오직 한개의 인스턴스 생성 허용하는 클래스 정의 기법 
public class Dog {
	private static Dog instance ; 
	
	
	//클래스 사하기위해 정의 
	//생성자 private지정 후아무것도 보완하지 않으면 절대로 Dog를 외부에서 사용할 수 없다 
	
	private Dog( ) {

		
	}
	
	public static Dog getInstance() {
		if(instance==null) instance=new Dog();
		return instance;
	} 
	
	//외부 객체가 접근할수있는 일반메서드 제공(생성자 막았음) 
	//아래의 메서드는 statice 수식자(modifer)가 붙지않았기떄문에 인스턴스 소속 메서드임 
	// new Dog()로 강아지 인스턴스 생성한 후 그 인스턴스 통해서만 접근간으 
	
	public void bark() {
		System.out.println("멍멍");
		
	}

}
