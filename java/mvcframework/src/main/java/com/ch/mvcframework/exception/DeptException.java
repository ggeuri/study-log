package com.ch.mvcframework.exception;

//자신만의 예외 만들기 

public class DeptException extends RuntimeException{
	//자바에서 부모 생성자는 상속 못받음. 호출필요. RuntimeException생성자 중 필요한거 호출하장 
	
	public DeptException(String msg) {
		super(msg); 
	}; 
	public DeptException(String msg, Throwable e) {
		super(msg,e); // 에러메시지 + 에러원인 심음  
	}; 
	public DeptException(Throwable e) {
		super(e); // 에러원인만 심음  
	}; 

}
