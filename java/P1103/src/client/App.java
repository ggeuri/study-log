package client;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("클라이언트 시작");
        System.out.println("닉네임을 입력해주세요 > "); // 접속하고 받아도 되지만, 접속하기 전에 받으면 안정성 UP
        String nickname = scanner.nextLine();

        System.out.println("서버에 접속을 시도합니다....");
        try(Socket socket = new Socket("172.30.1.71", 7777)) {
            System.out.println("서버에 접속 성공...");

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //프로토콜에 따라서 처음에는 무조건 닉네임을 전달(문자열)
            dos.writeUTF(nickname);

            ClientReceiveMessageThread clientReceiveMessageThread = new ClientReceiveMessageThread(socket);
            clientReceiveMessageThread.start();

            while (true) {
                System.out.println("메시지 입력 (0.종료)");
                String message = scanner.nextLine(); 
                if(message.equals("0")) break;

                dos.writeUTF(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("클라이언트 종료");
        scanner.close();
    }
}
