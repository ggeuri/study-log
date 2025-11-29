package com.ch.memberapp.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 평문의 비밀번호를 암호화시켜 해시로 결과 반환 
// java의 암호화처리는 javaEE, javaME 상관없이 javaSE에서 지원 
public class ShaManager {

    // 메서드 호출시 매개변수 평문으로 넘겨주면 암호화알고리즘 사용하여 그 값 반환 
    public static String getHash(String password){
        
        // String password = "minzino"; 
        StringBuffer hexString = new StringBuffer(); 

        try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 이 비밀번호 평문을 잘게 쪼개자 
            // password.getBytes("utf-8"); 

            // 아래의 매서드 수행하면 아직 암호화되지 않은 상태의 바이트 배열로 존재하는 데이터를 암호화시킴 
            // 32바이트 문자열 반환 
            byte[] hash = digest.digest(password.getBytes("utf-8"));//매개변수로 바이트 배열 원함 

            // for(int i = 0 ; i < hash.length; i++){
            //     // 아래의 hash[i] 에 혹여나 1로 시작하는 이진수 있다면 음수로 해석하므로 예상치못한 암호화 문자열 반환 
            //     // 따라서 byte[i]번째 데이터를 양수로 전환하려면 앞에 int 형의 32비트와의 and 연산 수행 0xff &
            //     // ex)[1000 0000] 
            //     String hex = Integer.toHexString( 0xff & hash[i]);
            // }

            for (byte b : hash) {
                String hex =  Integer.toHexString( 0xff & b);
                if(hex.length()==1){ // 2자리가 아니라 1자리수문자 나오면 64자 안맞으니 메워야함 = 0으로 메우장
                    hexString.append("0");
                }

                hexString.append(hex);//누적 
                
            }

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        
        return hexString.toString();
        
    }
    
    public static void main(String[] args) {
        String result = getHash("dog"); //static 메서드 호출하는 main () 메서드가 같은 클래스에 존재하므로 
        System.out.println(result);
    }

}
