package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ServerReceiveMessageThread extends Thread{

    private final ClientinfoDto clientinfoDto;

    public ServerReceiveMessageThread(ClientinfoDto clientinfoDto){
        this.clientinfoDto = clientinfoDto;
    }

    @Override
    public void run(){

        try (clientinfoDto.socket;){
            String helloMessage = clientinfoDto.nickname + "님이 접속하셨습니다.";
            broadcast(helloMessage);

            DataInputStream dis = new DataInputStream(clientinfoDto.socket.getInputStream());

            while (true) {
            // A로부터 메시지 수신 대기 
                String message = dis.readUTF(); 
                
                String forSendMessage = clientinfoDto.nickname + "] ";
                forSendMessage += message; 
                broadcast(forSendMessage);
            }
            
        } catch (Exception e) {
            //접속 종료 대비 
            ClientInfoListManager.list.remove(clientinfoDto);
            String message = clientinfoDto.nickname + "님이 퇴장하셨습니다.";
            broadcast(message);
        }  
    }
    
    private void broadcast(String message){
        //브로드캐스팅 .. A를 포함한 모든 접속자에게 메시지 전달 
        System.out.println("[서버로그]" + message);
        for (ClientinfoDto element : ClientInfoListManager.list) {
            try {
                DataOutputStream dos = new DataOutputStream(element.socket.getOutputStream());
                dos.writeUTF(message); 
                
            } catch (Exception e) {
                System.out.println("[서버 예외] 설마 발생하지 않겠지?");
            }
        
        }
    }
}
