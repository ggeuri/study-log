package p7;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class App {
    public static void main(String[] args) throws Exception{
        MessageDigest ma = MessageDigest.getInstance("SHA-1");
        String text = "asas"; // 
        byte[] digest = ma.digest(text.getBytes(StandardCharsets.UTF_8));


        //보기쉽게 문자열로 변환 
        StringBuilder stringBuilder = new StringBuilder(); 
        for(byte b : digest){
            stringBuilder.append(String.format("%02x", b));
        }
        System.out.println(stringBuilder.toString());

    }

}
