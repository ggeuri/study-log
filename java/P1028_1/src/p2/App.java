package p2;
import java.util.*;

public class App {
    public static void main(String[] args) {
        // List<StudentDto> linkeList = new ArrayList<>();
        List<StudentDto> linkeList = new LinkedList<>();

        linkeList.add(new StudentDto("한조"));
        linkeList.add(new StudentDto("한조1"));
        linkeList.add(new StudentDto("한조2"));
        linkeList.add(new StudentDto("한조3"));
        linkeList.add(new StudentDto("한조4"));

        linkeList.add(2,new StudentDto("끼워넣기")); // 삽입삭제... 이런게 있으면 ArrayList쓰는걸 다시생각해봐라 .. 
        linkeList.remove(1);// 삽입삭제... 이런게 있으면 ArrayList쓰는걸 다시생각해봐라 .. 
        
        System.out.println(linkeList.get(0).name);
        System.out.println(linkeList.get(1).name);
        System.out.println(linkeList.get(2).name);
        System.out.println(linkeList.get(3).name);
        
        System.out.println(linkeList.size());

        for(int i = 0 ; i < linkeList.size(); i++){
            System.out.println(linkeList.get(i).name);
        }

        for(StudentDto studentDto : linkeList){ 
            System.out.println(studentDto.name); // 향상된포문에서는 배열이 아니라 linkedList에 맞는 알고리즘으로 돌게됨
        }

        
        
    }

}

class StudentDto{
    StudentDto(String name){
        this.name = name;
    }
    String name;
    int age; 
    int score; 


}
