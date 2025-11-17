package p5;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        new Controller().run();
    }

}

class Controller{//기능추가한다고해서 Controller 건드리지마라 .....인데 지금은 Service수정하면 기능추가라고 Controller수정해야대
    private ServiceContainer serviceContainer = new ServiceContainer();

    public void run(){
        System.out.println("하루 일과 시작");
        for(Service service : serviceContainer.getServiceList()){
            service.Process();
        }
    }
}

interface Service{
    public void Process();

}

class ServiceContainer{
    private List<Service> list = new ArrayList<>(); // 배열대신에 쓰는 클래스. 앞으론 얘다. <>는 담고싶은 타입담아주면된당 

    public ServiceContainer(){
        list.add(new WakeUpService());//배열 순서대로 추가하다 
        list.add(new EatService());
        list.add(new StudyService());
        
    }
    public List<Service> getServiceList(){
        return list; 
    }
    
class WakeUpService implements Service{
    public void Process(){
        System.out.println("기상");
    }
}
class EatService implements Service{
    public void Process(){
        System.out.println("냠");
    }
}
class StudyService implements Service{
    public void Process(){
        System.out.println("공부");
    }
}

    

}

