package p3;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        // int value = (int)(Math.random()*100) + 1;        
        // System.out.println(value); // 가변 랜덤. 가볍게 쓸 때  

        Random random = new Random(1000); // 오 이게 씨드랜덤, 같은 씨드면 동일하게 랜덤값이 나온다..
        int value = random.nextInt(100) + 1;
        System.out.println(value);

        value = random.nextInt(100) + 1;
        System.out.println(value);

        double value2 = random.nextGaussian();
        System.out.println(value2);


        //확률까지 넣으려면? 


    }
}
