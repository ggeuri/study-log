package p8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class App {
    public static void main(String[] args) {

        // LocalDate date = LocalDate.now(); // 생성자는 안만들어옹 
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime when = LocalDateTime.of(2024, 5,3,0,0,0);


        System.out.println(today);
        System.out.println(when);

        System.out.println(today.minusDays(10)); // today에서 10뺀거 리턴해줌. 가능한 이유 ! 불변객체임 

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");//얘는 패턴,형태형 
        String text = today.format(dtf);
        System.out.println(dtf);

        System.out.println(text);

    }
}



