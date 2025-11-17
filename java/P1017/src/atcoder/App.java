package atcoder;

import atcoder.controller.Controller;

public class App {
    public static void main(String[] args) {
        //입력받는 클래스 하나 
        //takahashi ; b초동안a미터 걷고 c초동안 휴식. 
        //aoki 는 e초당 d미터걷고 f초휴식. x초일때 누가 앞서는가.
        // IoManager.restTimeAoki(3); 도ㅣㅁ됨 
        new Controller().run(); // new Controller()하면 참조값나오니까 거기에 접근연산자.넣고 접근해서 run()구동 

        
    }
}
