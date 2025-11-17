package p10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<Map<String, Object>> students = new ArrayList<>();
        Map<String,Object> student1 = new HashMap<>();
        Map<String,Integer> score1 = new HashMap<>();
        
        student1.put("name", "김영희");
        student1.put("age", 20);
        score1.put("eng", 80);
        score1.put("math", 80);
        student1.put("scores", score1);
        students.add(student1);
        
        Map<String,Object> student2 = new HashMap<>();
        Map<String,Integer> score2 = new HashMap<>();
        
        student2.put("name", "박민수");
        student2.put("age", 22);
        score2.put("eng", 70);
        score2.put("math", 70);
        student2.put("scores", score2);
        students.add(student2);


        /////////////////여기까지는 됨
        
        List<String> result = students.stream()
        .filter(a -> {
        Map<String, Integer> scores = (Map<String,Integer>) a.get("scores");
        Integer math = scores.getOrDefault("math", 0);
        return math>=80;
        })
        .map(a -> {
        String name = (String)a.get("name");
        Map<String,Integer> scores = (Map<String,Integer>) a.get("scores");
        Integer eng = scores.getOrDefault("eng", 0);
        return name+":"+eng;
        })     
        .sorted()
        .toList();


        List<Map<String,Integer>> results = students.stream()
        .map(stu -> {
        Map<String,Integer> scores = (Map<String,Integer>) stu.get("scores");
        int avg = (scores.getOrDefault("eng",0) + scores.getOrDefault("math",0)) / 2;
        String name = (String) stu.get("name");

        Map<String,Integer> map = new HashMap<>();
        map.put(name, avg);
        return map; 
        })
        .toList();

        System.out.println(results);

        // List<String> resultss = students.stream()
        // .sorted((a, b) -> {
        // Map<String,Integer> scoreA = (Map<String,Integer>) a.get("scores");
        // Map<String,Integer> scoreB = (Map<String,Integer>) b.get("scores");
        // return ; // 영어 점수 기준 내림차순은 모르겠고 
        // })
        // .map(stu -> (String) stu.get("name"))
        // .toList();
        
        double avg = students.stream()
        .mapToInt(stu -> {
        Map<String,Integer> scores = (Map<String,Integer>) stu.get("scores");
        Integer engscore = scores.getOrDefault("eng", 0);
        return engscore ; // 영어 점수 리턴
        })
        .average()
        .orElse(0);

        System.out.println(avg);



            
        }

    }

