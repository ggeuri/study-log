package p3;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class App {
    public static void main(String[] args) throws Exception{
        //파일 로드 
        File file = new File("/Users/rimu/Temp/aaa.dat");

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);

        // fis.read();// 얘도 바이트단위로 읽음 . 리드할때마다 하드를 긁음. 얘도 버퍼두는게좋음 -> 램으로 읽어서 -> 램은 IO에 대한 수명이 딱히 없음 

        System.out.println(dis.readInt()); //넣은거랑 순서 똑같이 읽어줘야됨 
        System.out.println(dis.readInt());
        System.out.println(dis.readUTF());
        System.out.println(dis.readUTF());



        dis.close();//하나만 클로즈해줘도됨. 
        bis.close();//하나만 클로즈해줘도됨. 
        fis.close();//하나만 클로즈해줘도됨. 



    }

}
