package p5;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class App {
    public static void main(String[] args) {
        Set<Integer> list = new TreeSet<>(); 
        

        while (list.size() < 6) {
            int value = (int)(Math.random()*46)+1; 
            
            if(!list.contains(value)){//중복제거 순차탐색 -> 탐색속도 o(n)
            list.add(value);
            }
            
        }

        for(int value : list){
            System.out.print(value + " "); // 순서대로나온다 오름차순 
        }

    }

}
