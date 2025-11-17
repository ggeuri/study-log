package p11;

import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        //1부터 100까지의 수 중 3의 배수는 제외하고 합을 구해보자 
        int sum = IntStream.rangeClosed(1, 100)
            .filter(x -> x%3 != 0)
            .filter(x -> x%2 != 0)
            .sum();

            System.out.println(sum);
    }

}
