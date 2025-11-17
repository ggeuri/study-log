package p15;

public class App {
    public static void main(String[] args) {

        Worker worker = new Worker("철수", 28);

        worker.introduce();

        
    }

}


// 🔹 문제 3

// 이름과 나이를 입력받아 “OOO님의 나이는 XX세입니다.” 출력
// 	•	Worker 클래스로 정의
// 	•	생성자에서 name, age 초기화 (this 사용 필수)
// 	•	introduce() 메서드로 출력

class Worker {
    String name; 
    int age; 

    Worker(String name, int age){
        this.name = name; 
        this.age = age; 
    }

    void introduce(){
        System.out.println(name + "님의 나이는 " + age +"세입니다.") ;
    }



}