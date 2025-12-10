package com.ch.mvcframework.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropTest {
	public static void main(String[] args) {
		//Map 상속받은객체 
		//단독적으로 파일 접근할 능력 없음. 따라서 java.io의 스트림객체들을 이용 
		
		//자바의 스트림 1. 방향에 따른 기준 (IO)
		//2. 데이터처리방법 : 바이트, 문자, 버퍼 
		
		Properties props = new Properties();  
		try {
			FileInputStream fis = new FileInputStream("/Users/rimu/Projects/javaEE_workspace/mvcframework/src/main/webapp/WEB-INF/servlet-mapping.txt");
			props.load(fis);// 이 시점부터 프로퍼티스 객체는 파일 내용 로드한 상태 
			String value = props.getProperty("/movie.do");
			System.out.println(value);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
