package p3;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {

        Map<String,TodoAdditionalData> todos = new HashMap<>();
        todos.put("1할일1", new TodoAdditionalData());
        todos.put("2할일22", new TodoAdditionalData());
        todos.put("3할일333", new TodoAdditionalData());
        todos.put("4할일4444", new TodoAdditionalData());

        //map 반복 돌리기 = keySet으로 뽑아서 Set으로 반복문 돌릴수있음. 근데 순서는 보장못한다.. 크냐작냐다..날짜순으로하고싶당.. set을 List로 바꿀수도 있음 

        Set<String> keys = todos.keySet();
        System.out.println(keys);

        for (String key : keys) {
            TodoAdditionalData data = todos.get(key);
            System.out.println("키: " + key + ", 값: " + data.후기);
            
        }
 
    }

}

// 할일 관리 프로그램 to-do리스트.. 
// 데이터 : 할일, 등록일, 완료일, 완료여부, 후기 
// 문제의도 : 날짜타입 order-by가능하니까 
// HashMap을 좀 썼으면 좋겠당.. 
// 할일을 Key로 써라 
// 맵은 반복문을 돌리는 목적이 아님. 얘네를 List안쓰고 출력하려면 (정렬안됨 ㅠ )

class TodoAdditionalData {
    LocalDate 등록일; 
    LocalDate 완료일;
    boolean 완료여부;
    String 후기;  

    TodoAdditionalData(LocalDate 등록일,LocalDate 완료일,boolean 완료여부,String 후기){
        this.등록일  = 등록일;
        this.완료일 = 완료일;
        this.완료여부 = 완료여부;
        this.후기 = 후기; 
    }
    TodoAdditionalData(){}

}

