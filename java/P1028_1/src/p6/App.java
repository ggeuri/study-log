package p6;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        //Map ... 키와 값을 쌍으로 저장 
        //키는 중복 불가 (덮어씌워짐) UUID같은거필요하겠눈뎅 . 값은 중복가능 
        Map<String, StudentDto> map = new HashMap<>();

        map.put("한조", new StudentDto("한조"));
        map.put("영희", new StudentDto("영희"));
        map.put("철수", new StudentDto("철수"));

        
        StudentDto s = map.get("한조");
        System.out.println(s.name);




        
    }

}

class StudentDto {
    String name;
    int age; 
    int score;

    StudentDto(String name){
        this.name = name;
    }
}