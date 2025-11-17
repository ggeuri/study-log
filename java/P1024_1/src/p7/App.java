package p7;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {// throws Exception : 
        //각 데이터간 변환
        //문자 - 숫자
        //숫자 - 날짜 
        //날짜 - 문자 

        // 1. 숫자 -> 문자 
        {
            int value = 15; 
            String result = Integer.toString(value);
            String result2 = value + ""; 
            String result3 = String.valueOf(value); 

            System.out.println(result);
            System.out.println(result2);
            System.out.println(result3);
        }

        // 2. 문자 -> 숫자 
        {
            String value = "20";
            int result = Integer.parseInt(value);

            System.out.println(result);
        }

        // 3. 숫자 -> 날짜 

        {
            int value = 10; 
            Date result = new Date(value);

            System.out.println(result);
        }

        //4. 날짜 -> 숫자 

        {
            Date value = new Date(); 
            long result = value.getTime();

            System.out.println(result);

        }

        //5. 문자 -> 날짜 

        {
            String value = "2025-10-03"; // 지정된 형식 아니면 exception뜸 

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 형태 먼저 지정 
            Date result = sdf.parse(value);
            // Date result1 = new Date(value);
            System.out.println("result 는 " + result);

        }

        //6. 날짜 -> 문자 
        {
            Date value = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String result = sdf.format(value);
            System.out.println(value);
            System.out.println(result);
        }

        //7.




    }

}
