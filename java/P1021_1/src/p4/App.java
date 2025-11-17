package p4;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        ServiceContainer serviceContainer = new ServiceContainer();
        System.out.println("반갑습니다");

        while (true) {
            System.out.println("메뉴");
            System.out.println("1");
            System.out.println("2");
            System.out.println("3");
            System.out.println("0. 종료");

            String command = "0"; 

            if(command.equals("0")){
                break;
            }
        

            Service service = serviceContainer.getService(command);
            service.run();

            // if(command.equals("1")){
            // }else if(command.equals("2")){
            // }else if(command.equals("3")){
            // }
        }   
        
    }

}

interface Service {
    public void run();
}

class StudentAddService implements Service { 
    public void run(){
        //학생등록로직
        System.out.println("학생등록이옹");
    }
}
class StudentListService implements Service { 
    public void run(){
        //학생등록로직
        System.out.println("학생목록이옹");
    }
}
class StudentSearchService implements Service { 
    public void run(){
        //학생검색로직
        System.out.println("학생검색이옹");
    }
}
class StudentRemoveService implements Service { 
    public void run(){
        //학생검색로직
        System.out.println("학생삭제옹");
    }
}


class ServiceContainer{
    private  Map<String, Service> map = new HashMap<>();

    public ServiceContainer(){
        map.put("1",new StudentAddService()); // 메모리를 생성해서 이걸 담겠다는 것... 배열같은거임  
        map.put("2",new StudentListService());   //"1,2,3"이 커멘드 put은 2라는 key로 담고 
        map.put("3",new StudentSearchService()); 
        map.put("4",new StudentRemoveService()); //기능추가
    }

    public Service getService(String command){
        return map.get(command); //get은 1넣으면 저 참조주소 리턴해주는거임 

    }
}
