package p9;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("5");
        list.add("8");
        list.add("10");
        list.add("13");
        list.add("20");

        double average = list.stream()
        .mapToInt(s -> Integer.parseInt(s))
        .filter(a -> a%2 == 0)
        .average()
        .orElse(0.0);

        System.out.println(average);
        
    }

}
