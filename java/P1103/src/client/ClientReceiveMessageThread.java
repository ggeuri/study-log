package client;

import java.io.DataInputStream;
import java.net.Socket;

public class ClientReceiveMessageThread extends Thread{
    private Socket socket;
    public ClientReceiveMessageThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            while (true) {
                String message = dis.readUTF();
                System.out.println(message);
            }
        } catch (Exception e) {}
    }
}
