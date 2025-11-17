package p2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String,Object> student1 = new HashMap<>();
        Map<String, Object> scores1 = new HashMap<>();

        student1.put("이름", "홍길동");
        student1.put("나이", 20);
        scores1.put("국어", 90);
        scores1.put("영어", 80);
        scores1.put("수학", 85);
        student1.put("scores", scores1);

        Map<String,Object> student2 = new HashMap<>();
        Map<String, Object> scores2 = new HashMap<>();

        student2.put("이름", "김영희");
        student2.put("나이", 21);
        scores2.put("국어", 95);
        scores2.put("영어", 70);
        scores2.put("수학", 90);
        student2.put("scores", scores2);

        Map<String,Object> student3 = new HashMap<>();
        Map<String, Object> scores3 = new HashMap<>();

        student3.put("이름", "박민수");
        student3.put("나이", 22);
        scores3.put("국어", 80);
        scores3.put("영어", 85);
        scores3.put("수학", 88);
        student3.put("scores", scores3);

        list.add(student1);
        list.add(student2);
        list.add(student3);

        for (Map<String,Object> student : list) {
            if(student.get("이름").equals("김영희")){
            System.out.println(student.get("이름"));
            System.out.println(student.get("나이"));

            Map<String,Object> score = (Map<String,Object>)student.get("scores");

            for (Map.Entry<String,Object> subject : score.entrySet()) {
                if(subject.getKey().equals("영어")){
                System.out.println("entrySet으로하면 : " + subject + ", 키랑 값 따로부르면 " + subject.getKey() + subject.getValue());}
            }}

            }

                
            }

            
        }



