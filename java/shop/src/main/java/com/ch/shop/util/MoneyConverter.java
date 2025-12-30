package com.ch.shop.util;

import java.text.NumberFormat;
import java.util.Locale;

//숫자 자료형으로 출력된 데이터를 통화 표시로 출력해주는 유틸 클래스 정의 
public class MoneyConverter {
	//인스턴스를 new로 생성할수있도록 메서드 static정의 
	private static final NumberFormat KRW_FORMAT = NumberFormat.getInstance(Locale.KOREA);
	
	public static String format(int price) {
		return KRW_FORMAT.format(price);
	}

}
