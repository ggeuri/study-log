package server;

import java.net.Socket;

public class ClientinfoDto {
    public String nickname;
    public Socket socket; 

    public ClientinfoDto(String nickname, Socket socket){
        this.nickname = nickname;
        this.socket = socket;
    }

}
