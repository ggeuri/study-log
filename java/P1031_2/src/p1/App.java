package p1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
        
        Map<String, Object> student1 = new HashMap<>();
        
        student1.put("name", "홍길동");
        student1.put("age", 20);
        student1.put("국어", 90);
        student1.put("영어", 80);
        student1.put("수학", 85);
        
        Map<String, Object> student2 = new HashMap<>();
        student2.put("name", "김영희");
        student2.put("age", 21);
        student2.put("국어", 95);
        student2.put("영어", 70);
        student2.put("수학", 90);
        
        Map<String, Object> student3 = new HashMap<>();
        student3.put("name", "박민수");
        student3.put("age", 22);
        student3.put("국어", 80);
        student3.put("영어", 85);
        student3.put("수학", 88);
        
        
        list.add(student1);
        list.add(student2);
        list.add(student3);
                    

        for(int i = 0 ; i < list.size(); i++){
            String name = (String) list.get(i).get("name");
            int age = (int) list.get(i).get("age");
            int score1 = (int) list.get(i).get("국어");
            int score2 = (int) list.get(i).get("영어");
            int score3 = (int) list.get(i).get("수학");
            System.out.println("학생 이름:" + name + "(" + age + "세)");
            System.out.println("국어:" + score1);
            System.out.println("영어:" + score2);
            System.out.println("수학:" + score3);
            
        }

            for(int i = 0 ; i < list.size(); i++){
            if(list.get(i).get("name").equals("김영희")){
                String name = (String) list.get(i).get("name");
                int age = (int) list.get(i).get("age");
                int score2 = (int) list.get(i).get("영어");
                System.out.println("학생 이름:" + name + "(" + age + "세)");
                System.out.println("영어:" + score2);   
            }
            
        }
        for(int i = 0 ; i < list.size(); i++){
            if(list.get(i).get("name").equals("박민수")){
                list.get(i).put("수학", 95);
                int score2 = (int) list.get(i).get("수학");
                String name = (String) list.get(i).get("name");
                int age = (int) list.get(i).get("age");
                System.out.println("학생 이름:" + name + "(" + age + "세)");
                System.out.println("수학:" + score2);   
            }
        }
        int sumScore = 0;
        double average = 0.0;
        for(int i = 0 ; i < list.size(); i++){ 
                int score2 = (int) list.get(i).get("국어");
                sumScore += score2 ; 
                
            }
            average = sumScore / (double)list.size();
            System.out.println("평균 국어 점수:" + average);   
        }

    }


