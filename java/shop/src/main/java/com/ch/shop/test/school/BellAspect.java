package com.ch.shop.test.school;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//아래의 클래스는 우리 애플리케이션에서 공통적이고도 전반적으로 사용되는 로직을 특정객체안에 DI처리하는 것이 아니라 
// 아예 독립적으로 하나의 관점으로 만들어 이 관점이 관여될 시점에 공통로직을 자동으로 호출할 수 있는 기술인 AOP를 구현하기 위함 
@Aspect
public class BellAspect {
	private Bell bell; 
	
	public BellAspect(Bell bell) {
		this.bell = bell; 
	}
	
	//AOP는 스프링자체의 기술이 아니라, 예전부터 자바기반의 기술 중 AspectJ라는 기술이 있었고, 스프링이 이걸사용 
	//라이브러리 추가 필요. aspectjweaver 받아옴. @Befor내부에 작성하는 표현식은 AspectJ의 문법이니 형식 따라야함 
	@Before("execution(* com.ch.shop.test.school.Student.*(..))") //앞의 * 반환형, 메서드위치. 뒤의*은 모든 메서드를 표현 
	public void ringBefore() {
		bell.ding();
		
	}
	
	@After("execution(* com.ch.shop.test.school.Student.*(..))")
	public void ringAfter() {
		bell.ding();
	}

}
