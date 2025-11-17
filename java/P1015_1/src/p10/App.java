package p10;

import p11.Pppp;

public class App {
    public static void main(String[] args) {
        Pppp p = new Pppp();

        StudentDto s1 = new StudentDto();
        s1.setName("철수");
        s1.setAge(14);
        s1.setScore(78);
        System.out.println(s1.getName());
        System.out.println(s1.getAge());
        System.out.println(s1.getScore());
    }

}

//Dto = 기능 없음. 데이터 묶는 용도의 클래스 (메서드 없음.)
class StudentDto {
    private String name; 
    private int age; 
    private int score; 
    
    // setter - write 권한
    public void setName(String name) { //외부에서 값 받으니 파라미터, 리턴타입없으니까 void
        this.name = name;
    }
    public void setAge(int age) { //외부에서 값 받으니 파라미터, 리턴타입없으니까 void
        this.age = age;
    }
    public void setScore(int score) { //외부에서 값 받으니 파라미터, 리턴타입없으니까 void
        this.score = score;
    }
    // getter - read 권한 
    public String getName() { //외부에서 받는 것 없으니까 (), 리턴타입 String
        return name; 
    }
    public int getAge() { //외부에서 받는 것 없으니까 (), 리턴타입 String
        return age; 
    }
    public int getScore() { //외부에서 받는 것 없으니까 (), 리턴타입 String
        return score; 
    }
}