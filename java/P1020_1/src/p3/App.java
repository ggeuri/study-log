package p3;

public class App {
    public static void main(String[] args) {
    Team[] teams = new Team[5]; 
    }

}

//조직도 팀 하위팀 - 하위팀.. 총괄 - 실 - 팀 - 파트 등등 

class Team {
    String name; 
    //기타정보들
    Team[] teamList = new Team[5];//자료 구조 자체가 재귀임. 팀안에 팀이 또 있으니까(똑같은거 반복)
    Person[] presonList = new Person[5];
}
class Person {
    String name; 
    //기타정보들
}
