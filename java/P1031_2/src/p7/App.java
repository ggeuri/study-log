package p7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        
        Map<String, Object> student1 = new HashMap<>(); 
        student1.put("이름", "홍길동");
        student1.put("나이", 20);
        student1.put("점수", 90);
        list.add(student1);

        Map<String, Object> student2 = new HashMap<>(); 
        student2.put("이름", "홍");
        student2.put("나이", 25);
        student2.put("점수", 70);
        list.add(student2);
        
        Map<String, Object> student3 = new HashMap<>(); 
        student3.put("이름", "영희");
        student3.put("나이", 21);
        student3.put("점수", 80);
        list.add(student3);
        
        for (Map<String,Object> e : list) { // foreach문으로 출력 
            System.out.println("이름: " + e.get("이름") + ", 나이: " + e.get("나이") + ", 점수: " +e.get("점수"));
        }
        
        for (Map<String,Object> list2 : list) {
            if((Integer)list2.get("점수")>=90){
                System.out.println("90점 이상 학생: " + list2.get("이름"));
            }
            
        }
        //keySet활용? _Map안에 Map넣었을때나 해야하는거 아닌지
        //stream에 filter넣으려고했는데 잘 안됐음

        list.stream()
        .filter(e -> (Integer)e.get("점수")>=90)
        .forEach(e -> System.out.println("Stream 90점이상 " + e.get("이름")));
        
        
    
    }

}
