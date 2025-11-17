package p7;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class App {
    public static void main(String[] args) {
        // List<String> list = new ArrayList<>(); 
        List<String> list = new Vector<>(); // Vector는 ArrayList와 동일하지만 모두 sync되어있음 : 안정성 보장 

        list.add("11111");
        list.add("11111");
        list.add("11111");
        list.add("11111");
        list.add("11111");
    }

}
