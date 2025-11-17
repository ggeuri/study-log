package p6;

public class App {
    public static void main(String[] args) {
        new Controller().controllerMethod();
        System.out.println("프로그램종료");
    }
}

class Controller{
    public void controllerMethod() {
        new Service().ServiceMethod();

    }
}
class Service{
    public void ServiceMethod() {
        try{
            new Repository().RepositoryMethod();
        }catch(Exception e){
            System.out.println("에러 처리..");
            return; //파이널리가 있는데 리턴?
        }finally{
            //그 어떠한 경우에도 실행됨.
            System.out.println("finally에서 실행되는 case");//리턴이 있건 없고 무조건 나온다. 아항 ! 서비스끝넣어놓으면 걍 나오는거네 
            //사실 다른 용도로는 잘 안쓰고 메모리 해제하는용도로씀 

        }
        System.out.println("서비스 끝");

    }
}
class Repository{
    public void RepositoryMethod() {
        int result = 10/0; // 레포에서 Exception
        System.out.println(result);

    }
}