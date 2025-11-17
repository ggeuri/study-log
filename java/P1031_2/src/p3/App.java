package p3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();


        //학생 한번에 생성 
        Map<String, Object> student1 = new HashMap<>(); 
        Map<String, Object> student2 = new HashMap<>();
        Map<String, Object> student3 = new HashMap<>();

        //학생 점수 한번에 생성
        Map<String, Integer> score1 = new HashMap<>(); 
        Map<String, Integer> score2 = new HashMap<>(); 
        Map<String, Integer> score3 = new HashMap<>(); 


        //길동이 
        student1.put("이름", "홍길동");
        student1.put("나이", 20);
        score1.put("국어", 90);
        score1.put("영어", 80);
        score1.put("수학", 85);
        student1.put("score", score1);
        list.add(student1);
        
        //영희
        student2.put("이름", "김영희");
        student2.put("나이", 21);
        score2.put("국어", 95);
        score2.put("영어", 70);
        score2.put("수학", 90);
        student2.put("score", score2);
        list.add(student2);
        
        //민수
        student3.put("이름", "박민수");
        student3.put("나이", 22);
        score3.put("국어", 80);
        score3.put("영어", 85);
        score3.put("수학", 88);
        student3.put("score", score3);
        list.add(student3);


        //단계 전체 학생 출력 
        list.forEach(students -> {String name = (String)students.get("이름");
            int age = (int)students.get("나이");
            System.out.println("학생 이름: " + name + "(" + age + "세)");
            Map<String,Integer> scores = (Map<String,Integer>) students.get("score");
            
            scores.forEach((k,v) -> {
                System.out.println(k + ": " + v);});
        
        });


        

        // for (Map<String,Object> students : list) {
        //     String name = (String)students.get("이름");
        //     int age = (int)students.get("나이");
        //     System.out.println("학생 이름: " + name + "(" + age + "세)");

        //     Map<String,Integer> scores = (Map<String,Integer>) students.get("score");
        //     students.get("score"); //이거는 오브젝트나오고 담긴건 Map<String, Integer> 이거란말야 
        //     //Map<String,Integer>니까 keySet()하면 국어영어수학튀어나오고 
        //     //EntrySet()하면 국어=80 영어=95 처럼 K=V 매핑돼서나옴. 근데 얘는 반환타입이 뭐지..?

        //     for (String subject : scores.keySet()) { // 아 scores가 Map이니까 여기서 get하면 value가나오고 get의 키값이 subject구나 
        //         int value = scores.get(subject);
        //         System.out.println(subject + ": " + value);
        //                     }

        //     for (Map.Entry<String,Integer> subject : scores.entrySet()){
        //         System.out.println(subject);
        //     }
        

        // }
    }

}
