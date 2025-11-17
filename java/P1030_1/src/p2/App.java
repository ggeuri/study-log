package p2;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class App {
    public static void main(String[] args) throws Exception {
        // 남은 수업 : 파일입출력, 스레드, 네트워크, 
        // 1. 파일 입출력 
        // 목표: 데이터를 파일에 저장할 수 있다. 

        int a = 0xAABBCCDD; 
        System.out.println(a);

        // File file = new File("\\Users\\rimu\\Temp\\aaa.dat") ; // 윈도우 
        File file = new File("/Users/rimu/Temp/aaa.dat"); // 맥. Temp는 만들어둬야. 자바가 이 파일에 대한 제어권을 뺏어옴. 클로즈 전까지. 윈도우는 파일삭제못한다함. 
        // System.out.println(file.exists());

        
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos); // 쉽게쓰기위한 필터..? 

        

        dos.writeInt(10); //fos.write힘드니까 알아서 나눠서 해준다 
        dos.writeInt(16); //물리적으로 하드에 그때그때 저장하는거임. 위험함  -> 버퍼를 둬야함  
        dos.writeUTF("안녕하세요");
        dos.writeUTF("반갑습니다");

        // fos.write(a >> 0); //거꾸로담기 0,8,16,24 . 정석담기 24,16,8,0
        // fos.write(a >> 8); 
        // fos.write(a >> 16); //현재는 API가 해줌 
        // fos.write(a >> 24); // 한바이트만 저장된대.인트는 4바이트니까. 4번에 걸쳐 쪼개 저장. 4바이트를 비트 연산해서 저장 . 인트를 저장하는 가장 정상적인 방식.[옛날방식]
        
        // Thread.sleep(10000); //파일 삭제모
        
        dos.close();//역순클로즈  버퍼 꽉 안차서 저장해줘야댐 
        bos.close();
        fos.close();

        //Stream나오면 배열이다. 
        //input은 남의 데이터 읽는것 로드. output 내 데이터를 출력 - 세이브
        //Stream 싹 다 byte 스트림임 .

    }
}
