package p9;

public class App {
    public static void main(String[] args) {
        //오버라이딩 (=재정의) 
        AAA aaa = new BBB(); // 다형성(문법 - 런타임/실제 메모리생성)
        aaa.test(); 
        // 문법적으로 성립되는 이유는 변수가 AAA고 AAA에 test가 있어서. 
        // 그런데 런타임시에는 BBB를 따라가니까 오버라이딩된게있으니까 BBB 호출함. 만약 오버라이딩된것없으면 AAA의 test 호출함.   
        // 만약 AAA에 있는 test를 지웠다면 컴파일에러임. 존재는 해야 문법이 성립하지. 후에 런타임 때 옮겨가고 

        // aaa.test(); 얘는 문법적으로 호출 못하는 케이스 문법은 타입따라 간다 

        //오버라이딩은 문법과 런타임을 구분할 줄 알아야 함. 
    }

}


class AAA{

    void test(){
        System.out.println("AAA의 test 호출됨");
    }
}

class BBB extends AAA{
    void test(){
        System.out.println("BBB의 test 호출됨"); // 이게 오버라이딩. 덮어버리는 것 . 문법이 완벽하게 똑같아야 함
        //상속받되 그중에서 test라는 메서드를 오버라이드했따. 
    }
}
