package com.ch.mvcframework.movie.model;

// 자바SE든  자바EE든 자바ME든.....어떤 플랫폼이든간에 재사용 가능한 중립적 코드 정의 = Model

public class MovieManager {
	
	
	public String getAdvice(String movie) {
		String msg = "선택한 영화가 없음"; 
		if(movie !=null){
			if(movie.equals("귀멸의칼날")){
				msg = "일본 애니메이션"; 
			}else if (movie.equals("주토피아2")){
				msg = "디즈니 애니메이션"; 
			}else if (movie.equals("위키드2")){
				msg = "뮤지컬 영화"; 
			}
		}
		return msg; 
	}
	

}
