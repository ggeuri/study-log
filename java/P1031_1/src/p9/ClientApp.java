package p9;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in); 

        System.out.println("클라이언트 시작");
        
        System.out.println("서버에 접속 시도");
        Socket socket = new Socket("172.30.1.71", 7777); //  (접속할 IP, port) // 소켓에는 OutputStream도 있고 InputStream도 있다.
        System.out.println("서버에 접속 완료");


        while (true) {
            System.out.print("전달 값 입력 (0. 종료)> ");
            String message = scanner.nextLine(); // 얘를 서버로 전달 ! 
            
            if(message.equals("0"))break;
            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());//바이트스트림 너무 어려우니까 변환하는 DOS사용 
            dos.writeUTF(message);
            System.out.println("서버에 메세지 전달 완료.");
        }

        System.out.println("클라이언트 종료");
    }

}
