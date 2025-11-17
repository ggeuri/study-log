public class P3 {
    public static void main(String[] args) {

        int a = 10; //생성자에서도 쓰고싶은데 a를 못쓴당 위치가 다르잖아 {}. 값을 넘겨주고싶어서 매개변수를 사용한다 

        System.out.println("프로그램 시작");
       
        Student1 s1 = new Student1("철수",20,70); // 선언해줄 수 있어.. 
        Student1 s2 = new Student1("영희",10,90); 
        Student1 s3 = new Student1("길동",10); 
        Student1 s4 = new Student1(); // 이 형태의 생성자 없으니까 오류지만 하나 더 만들어주면된단거네  

        System.out.println(s1.name);
        System.out.println(s2.name);
        System.out.println(s3.name);

        System.out.println("프로그램 끝");
    }
}

class Student1 {
    // 속성(필드), 인스턴스 변수, 멤버 변수 - 설계의 핵심 
    String name; 
    int age; 
    int score; 

    // 생성자_요즘 거의 안쓰긴 함 : 의도 =  일반적으로는 초기화 목적 
    // 인스턴스 생성시에만 딱 1회 호출된다 (오버로딩되더라도 하나만 호출함.맞는 매개변수에 따라 호출되겠지)
    Student1(String name, int b, int c) { // 소괄호 안에 변수 선언 가능. 이를 매개변수(파라미터)라 한다. // 얘를 선언하면 인스턴스 초기화할때마다 int 값 필요함. // 생성자에 매개변수가 선언되어있으면 그 선언 형태에 따라 인스턴스도 맞춰서 넣어줘야된다.   
        // 지역변수 관리 따로 함.   
            System.out.println("매개변수 3개짜리 호출");
            this.name = name; 
            age = b; 
            score = c;  
            // 여기 총 6개의 메모리 사용가능 this에 속한 애 name,age,score. a,b,c
            //this빼도 됨. 그런데 지역변수가 우선시되기때문에 만약에 중복된 이름의 변수가 지역변수에 있다면.. 지역변수를 부름.(지역변수로서의 name, 매개변수로서의 name)
            //this를 빼면 단축문법임. 실제의도는 this.name임. this가 늘 숨어있다는걸 인지해야 함.
            }
    Student1(String name, int age) { 
        System.out.println("매개변수 2개짜리 호출");
        this.name = name; 
        this.age = age; 

        // this(name, age, 0);   // 이거뭐지? 
    }
     Student1(){
        
     }


}



//생성자 오버로딩 (=생성자 2개이상 )
//오버로딩에서는 타입 순서, 타입을 중복으로 체크함. 규칙적으로 인스턴스에서 인식가능하니까 