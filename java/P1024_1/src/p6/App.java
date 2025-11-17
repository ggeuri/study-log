package p6;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        //날짜 - 사실상 숫자 
        //Date - LocalDate, LocalDateTime, LocalTime

        Date date = new Date(); //현재 시간, 날짜 
        System.out.println(date);

        // Date aaa = new Date();
        // System.out.println(aaa);


        //2025.10.24일
        System.out.println(date.getYear()); // 왜 125나옴? 
        System.out.println(date.getMonth()); // 왜 9 나옴?  항상 1이 빠짐 그러니까 +1 해주기 
        System.out.println(date.getDate());
        System.out.println(date.getDay()); //요일 그럼 일요일도 0인가..? 시작이 0이니까 

        //시계열 데이터는 같다 비교 잘안함 

    }

}
