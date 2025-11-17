package atcoder.controller;

import atcoder.service.Service;

public class Controller {
    Service service = new Service();

    public void run() {
        //Controller 흐름 
        //1. 입력받고 - IoManager 통해 입출력 구현, 러너에서 러너속성정의해
        //2. 비즈니스 로직 - 타카하시랑 아오키 입력값이랑 러너한테 값 받아서 서비스(기존스타디움)에서 비즈니스로직 돌려
        //3. 결과값 인출 -  서비스에서 나온 결과를 컨트롤러에서 받아서 IoManager출력호출
        //4. App에서 new Controller().run();

    }

    

}
