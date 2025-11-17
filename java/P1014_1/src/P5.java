public class P5 {
    public static void main(String[] args) {
        
        Student3 s1 = new Student3("철수");
        Student3 s2 = new Student3("영희");

        s1.study(3);//s1과 s2는 메모리 위치가 다르니까 this가 참조하는 주소가 다름. 로직은 같지만 단 하나의 차이.. 
        s1.study(2);
        s2.study(1);
        s2.study(1);
        s2.study(1);
        s1.study(5);

        s1.printResult(); 
        s2.printResult();
        
    }
}

class Student3 {
    String name;  // 보통 속성이 3개있으면 빈생성자 1개, 속성에 맞는 생성자 3개 생성 
    int totalTime; 
    int score; 

    Student3(String name){
        this.name = name;

    }

    void study(int hour){
        this.totalTime += hour;
        this.score += hour * 3; 
    }

    void printResult(){ //메서드 쓸 때 매개변수가 없으면 응집도가 낮다고 표현한다 함. 
        System.out.println(this.name + "는 " + this.score+ "점이고, " + this.totalTime + "시간 공부하였습니다.");
    }

}