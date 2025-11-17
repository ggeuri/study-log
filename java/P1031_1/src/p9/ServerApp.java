package p9;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) throws Exception{
        //0~65536까지의 포트 가능 ? 하나의 컴퓨터는 포트 1 
        System.out.println("서버 시작 ");
        ServerSocket serverSocket = new ServerSocket(7777);

        while (true) {
            Socket socket = serverSocket.accept(); //클라이언트로부터 접속대기 
            System.out.println(socket.getInetAddress().getHostAddress() + "가 접속했습니다.");
            new RecvThread(socket).start(); // 오.... 스레드.. 
        }
    }

}

class RecvThread extends Thread {
    private Socket socket;
    public RecvThread(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run(){
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String ip = socket.getInetAddress().getHostAddress();
            while (true) {
                String message = dis.readUTF();
                System.out.println( ip + "메세지: " + message);
                
            }
        } catch (Exception e) {
            System.out.println("누군가 종료했습니다.");
        }
    }

}