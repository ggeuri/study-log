package p2;

public class App {
    public static void main(String[] args) {
        //대표적인 API: String API 
        String str1 = "aaaa, bbb, ccc";

        String toUpperStr1 = str1.toUpperCase();

        System.out.println(toUpperStr1);

        String searchWord = "bts";
        String targetWord = "Bts";

        if(searchWord.toUpperCase().equals(targetWord.toUpperCase())){
            System.out.println("true");
        }

        int lengthSearchWord = searchWord.length();
        System.out.println(lengthSearchWord);

        String[] arr = str1.split(",");
        searchWord.trim();

        System.out.println(arr[1]);

        "dasdfsd".startsWith("da");

        String stringStr = "가나다라마바사".substring(4,6);
        System.out.println(stringStr);

        int whereWord = "costco".indexOf("co");
        int whereEndWord = "costco".indexOf("tco");
        String costcoString = "costco".substring(whereWord,whereEndWord);

        System.out.println(costcoString);

        ///////////////////////////////////////////////////////////////////////////
        
        //static정적메서드

        int a = 10; 
        int b = 20;
        String num = String.valueOf(10);

        System.out.println(num);

        String result = String.format("%d X %d = %d", a,b,a*b);

        System.out.println(result);

        String[] qqqq = {"안녕하세요.","반갑습니다.","야호"};

        String dpd = String.join(" ", qqqq);

        System.out.println(dpd);




    }

}
