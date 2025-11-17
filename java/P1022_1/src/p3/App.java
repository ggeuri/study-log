package p3;

public class App {
    public static void main(String[] args) {
        AAA aaa = new Some(); //여기에 들어갈 인스턴스변수는 인터페이스를 상속받는 클래스가 인스턴스변수다
        aaa.test();
        
        // AAA aaa2 = new AAA();//불가  
        AAA aaa2 = new AAA(){ 
            //원래는 인터페이스기때문에 인터페이스로 재정의 불가능함. 대신 익명클래스 ㄱ하면 가능. 중괄호 열어버리면 인스턴스가 아니라 상속받은 클래스를 정의하고 그담에 인스턴스생성이라 봐야함. 
            //익명클래스의 선언으로 이 안에 다 들어있는거임. 단축문법 = 메서드 한줄가지고 클래스정의하기 싫어가지고 단축시킴.
            public void test() {
                System.out.println("하이");
            }
        };
        aaa2.test();

        AAA aaa3 = () -> System.out.println("헬로"); // 애로우펑션, 람다식. 더 단축시킨것. 딱 한줄만써야되는 케이스때만 단축문법하는거임. 
        aaa3.test();
    }
}

//익명객체 - 사실상 람다식으로 대체된 문법
interface AAA{ 
    public void test();
}

class Some implements AAA{
    public void test(){
        System.out.println("야호");
    }
}
