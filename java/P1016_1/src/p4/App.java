package p4;

public class App {
    public static void main(String[] args) {
        SomeComponent sc = SomeComponent.getInstance();
        
        sc.func();

    }

}


//싱글톤 예시
//Component = 기능 위주의 클래스, 대부분의 경우 한번만 생성, 재활용 (두번생성좋지않아)
//Dto, Vo = 데이터 구조의 클래스, 많이 자주 생성됨 
class SomeComponent{


    private static final SomeComponent instance = new SomeComponent();

    public static SomeComponent getInstance(){

        return instance;
    }

    private SomeComponent(){    }

    // 실제 기능 
    public void func(){
        System.out.println("기능 실행");
    }

}

//생성자 구현 -> private으로 만들면 인스턴스 생성 막음 -> 근데 한번은 생성돼야함
// -> private static SomeComponent instance = null; 

//예시로 Math, System 클래스는 생성자 다 막았음 static클래스는 생성자 다 막음  