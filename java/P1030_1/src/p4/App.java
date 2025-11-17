package p4;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class App {
    public static void main(String[] args)throws Exception{
        // bmp기준의 파일 생성
        // 파일명: aaa.bmp
        // 해상도: 100x100 

        File file = new File("/Users/rimu/Temp/bbb.bmp");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);

        //헤더
        //시그니처 BM
        dos.writeByte('B');
        dos.writeByte('M');

        //파일 총 크기 
        dos.writeByte(30054 >> 0);
        dos.writeByte(30054 >> 8);
        dos.writeByte(30054 >> 16);
        dos.writeByte(30054 >> 24); 
        // 예약 = 0
        dos.writeByte(0 >> 0);
        dos.writeByte(0 >> 8);//리틀인디안 
        // 예약 = 0
        dos.writeByte(0 >> 0);
        dos.writeByte(0 >> 8);//리틀인디안 

        //Raw 데이터 시작 인덱스 .. bmp파일포맷(위키백과..에 맞춰서만드는중임 
        dos.writeByte(54 >> 0); 
        dos.writeByte(54 >> 8);
        dos.writeByte(54 >> 16);
        dos.writeByte(54 >> 24);

        //2차헤더시작
        //헤더크기(40)
        dos.writeByte(40 >> 0); 
        dos.writeByte(40 >> 8);
        dos.writeByte(40 >> 16);
        dos.writeByte(40 >> 24);

        //비트맵 가로
        dos.writeByte(100 >> 0); 
        dos.writeByte(100 >> 8);
        dos.writeByte(100 >> 16);
        dos.writeByte(100 >> 24);

        //세로
        dos.writeByte(100 >> 0); 
        dos.writeByte(100 >> 8);
        dos.writeByte(100 >> 16);
        dos.writeByte(100 >> 24);

        //색상 평면 수 
        dos.writeByte(1 >> 0);
        dos.writeByte(1 >> 8);

        //픽셀당 비트 수 
        dos.writeByte(24 >> 0);
        dos.writeByte(24 >> 8);

        //압축 방법.
        dos.writeByte(0 >> 0); 
        dos.writeByte(0 >> 8);
        dos.writeByte(0 >> 16);
        dos.writeByte(0 >> 24);
        
        //이미지 크기
        dos.writeByte(30000 >> 0); 
        dos.writeByte(30000 >> 8);
        dos.writeByte(30000 >> 16);
        dos.writeByte(30000 >> 24);
        
        //가로 해상도
        dos.writeByte(0 >> 0); 
        dos.writeByte(0 >> 8);
        dos.writeByte(0 >> 16);
        dos.writeByte(0 >> 24);

        //세로 해상도
        dos.writeByte(0 >> 0); 
        dos.writeByte(0 >> 8);
        dos.writeByte(0 >> 16);
        dos.writeByte(0 >> 24);

        //팔레트색상수
        dos.writeByte(0 >> 0); 
        dos.writeByte(0 >> 8);
        dos.writeByte(0 >> 16);
        dos.writeByte(0 >> 24);

        //사용된 중요한 색상수
        dos.writeByte(0 >> 0); 
        dos.writeByte(0 >> 8);
        dos.writeByte(0 >> 16);
        dos.writeByte(0 >> 24);

        //RAW Data
        for(int y = 99 ; y >= 0 ; y--){
            for(int x = 0 ; x < 100 ; x++){
                // if (y == (int)(Math.pow(x - 50, 2) / 25)) {
                if (y == (int)(Math.pow(x - 50, 2) / 25)) {
                    dos.writeByte(0x00); //얘도 역순 B
                    dos.writeByte(0x00); // G 
                    dos.writeByte(0xFF); // R
                    continue;
                }
                dos.writeByte(0xFF); //얘도 역순 B
                dos.writeByte(0xFF); // G 
                dos.writeByte(0xFF); // R
            }
        }

        dos.close();
        bos.close();
        fos.close();

        System.out.println("정상적으로 이미지가 생성되었습니다.");

    }

}
