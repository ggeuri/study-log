package p18;

public class App {
    public static void main(String[] args) {

    Student student1 = new Student(); 
    Student student2 = new Student(); 
    
    System.out.println(Student.totalCount);

    }
}

// 두 개의 Student 객체가 있을 때,
// static int totalCount 변수를 사용하여 생성된 학생 수를 카운트하시오.

class Student {
    static int totalCount = 0;

    Student() {
        totalCount++;
    }
}