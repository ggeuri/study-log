package p17;

public class App {
    public static void main(String[] args) {
       
        String targetFile = "data.txt";

        String extension = targetFile.substring(targetFile.indexOf("."));

        System.out.println("확장자는 " + extension + "입니다.");

        
    }

}
// 입력: data.txt  
// 출력: 확장자는 .txt 입니다.