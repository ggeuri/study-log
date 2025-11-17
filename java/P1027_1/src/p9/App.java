package p9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class App {
    public static void main(String[] args) {
        int value = Integer.parseInt("19");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("C:/aaa/bbb.text");//얘도 Unhandle . 아래로내리면 에러생김 
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if(fileOutputStream != null) {
                try{
                    fileOutputStream.close();
                } catch(Exception e){
                    e.getStackTrace();
                }
            }
        
        }
        
        try(FileOutputStream fileOutputStream2 = new FileOutputStream("C:/aaa/bbb.text")){
        } catch(Exception e){
            e.printStackTrace();
        }

 
        try {
            Socket socket = new Socket("111.111.111.111", 8080); //두가지 Exception을 해결해야함 
            // 네트워크, 파일 입출력 등 물리적으로 문제가 있고 소프트웨어적으로 할 수 있는 일이 없는 경우는 Exception을 상속받음 
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } 
        
    }

}
