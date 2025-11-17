package p10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        //자료구조 : Collection Framework 
        List<String> list = new ArrayList<>(); // 문자열을 담은 배열을 만들고 싶어 

        list.add("한조, 0"); //add 가 제일중요
        list.add("한조, 1"); //add 가 제일중요
        list.add("한조, 2"); //add 가 제일중요
        list.add("한조, 3"); //add 가 제일중요

        System.out.println(list.size()); // 길이가 보인당 

        for(String e : list){
            System.out.println(e);
        }

        //////////////////////////
        
        Map<String, Object> map = new HashMap<>(); // 
        
        map.put("이름", "한조"); //
        map.put("나이", 30); //
        map.put("점수", 99); //
        System.out.println("이름은 " + map.get("이름")+ ", 나이는" + map.get("나이")+ ", 점수는 " + map.get("점수"));

    }

}
