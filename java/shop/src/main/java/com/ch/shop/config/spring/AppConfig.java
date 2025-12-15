package com.ch.shop.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ch.shop.test.food.Cook;
import com.ch.shop.test.food.Fripan;
import com.ch.shop.test.food.Induction;
import com.ch.shop.test.school.Bell;
import com.ch.shop.test.school.BellAspect;
import com.ch.shop.test.school.Student;

//스프링에서 전통적으로 DI 구현하기 위해 개발자가 필요로하는 자바 클래스(bean) XML에서 해옴 
//하지만 최근의 개발 방법은 자바 

@Configuration //로직용 아님. 설정용클래스임을 선언 
@EnableAspectJAutoProxy //AOP가능하게 만드는 어노테이션
public class AppConfig {
	
	//애플리케이션에서 사용할 모든 객체들을 등록 
	@Bean
	public Fripan friPan() {
		return new Fripan(); 
	}
	
	@Bean
	public Induction induction() {
		return new Induction();
	}
	
	
	//아래와 같이 빈들 간의 관계를 표현해놓은 것을 weaving한다고 함 
	@Bean
	public Cook cook(Fripan pan) {
		return new Cook(pan);
	}
	
	@Bean
	public Bell bell() {
		return new Bell();
	}
	
	@Bean
	public Student student() {
		return new Student();
	}
	
	@Bean
	public BellAspect bellAspect(Bell bell) {
		return new BellAspect(bell);
	}
	

}
