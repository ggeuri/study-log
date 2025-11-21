package com.ch.gallery.util;
// 문자열 처리와 관련되어 자주 사용되는 기능 모아놓은 유틸 클래스 
public class StringUtil {
	
//	주어진 파일경로에서 확장자만을 추출 
	public static String getExtendFrom(String name) {
		int lastIndex = name.lastIndexOf("."); 
		
		String extend = name.substring(lastIndex+1,name.length());
		return extend;
		
	}

}
//int lastDot = name.lastIndexOf("."); 
//
//String extend = name.substring(lastDot+1,name.length());
//out.print(extend);