package com.ch.shop.test.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ch.shop.config.spring.AppConfig;
import com.ch.shop.test.food.Cook;
import com.ch.shop.test.school.Student;

public class Appmain {
	public static void main(String[] args) {
		//스프링의 ApplicationContext는 개발방법에 따라 여러가지 하위 자료형 지원해준다 
		//ex ) 빈 설정파일이 xml일 경우엔 ClasspathXMLApplicationContext
		// 빈 설정파일이 자바 클래스면 AnnotaionConfigApplicationContext
		
		// 개발자가 설정해놓은 클래스 읽어들여야하니 생성자의 매개변수로 전달 App.class 
		//아래의 생성자 호출되는 순간 개발자가 설정파일에@Bean으로 명시해놓은 객체들의 인스턴스를 생성하여 보관 =Bean Container 
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); 
		Cook cook = (Cook)applicationContext.getBean("cook");
		cook.makeFood();
		
		Student student = (Student)applicationContext.getBean("student");
		student.goHome();
		student.gotoSchool();
		student.haveLunch();
		student.study();
		
		
	}

}
