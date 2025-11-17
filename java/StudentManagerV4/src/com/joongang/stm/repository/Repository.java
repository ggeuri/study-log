package com.joongang.stm.repository;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.joongang.stm.dto.StudentDto;
import com.joongang.stm.util.IoManager;

//컴포넌트
//역할 : 저장소 - 데이터 입출력 담당 
//이부분은 사실상 DB쿼리로 변경될 것 . 직접 구현할 일 없음
// 만약 전체가 부담되면 Repository는 복사해서 시작해도됨 .
public class Repository {
    private List<StudentDto> list = new ArrayList<>();

    public void save(StudentDto studentDto){
        list.add(studentDto);
    }

    public List<StudentDto> findAll(){
        return list;
    }

    public List<StudentDto> findByNameContaining(String searchWord){
        List<StudentDto> newList = new ArrayList<>(); 

        for (StudentDto studentDto : list) {
            if(studentDto.getName().contains(searchWord)){
                newList.add(studentDto);
            }
        }

        return newList; 

    }


    public int removeByname(String removeName){
        // 참고 여기 코드는 우선순위아님 사실상 List의 remove를 사용할 일은 거의 없음
        int deleteCount = 0;
        for(int i = 0 ; i < list.size(); i++){
            if(list.get(i).getName().equals(removeName)){
                list.remove(i);
                i--;
                deleteCount++;
            }
        }
        return deleteCount; 
    }


    public void saveToFile(){
        File file = new File("/Users/rimu/Temp/stm.dat");

        try (FileOutputStream fos = new FileOutputStream(file); // 클로즈하려고 finally쪽에 넣어줌 
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream dos = new DataOutputStream(bos)
        ){

            //파일포맷 = 메타데이터(헤더)+RAW DATA
            //_메타데이터 = 파일이 어떻게 되어있는지 설명해주는..   
            //파일포맷을 만들때는 보통 시그니처를 먼저 넣음(사명등..)

            //==============================================메타데이터 (헤더) 시작 
            dos.writeByte('S');//시그니처. 로드할 때를 위해서
            dos.writeByte('T');//시그니처
            dos.writeByte('M');//시그니처
            
            dos.writeInt(list.size()); // 파일에 몇명 학생 존재하는지
            //==============================================메타데이터 (헤더) 끝

            for(StudentDto studentDto : list){
                dos.writeUTF(studentDto.getName());
                dos.writeInt(studentDto.getAge());
                dos.writeInt(studentDto.getScore());
            } // 읽는 사람을 고려하지 않은 저장케이스 

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadFromFile(){
        File file = new File("/Users/rimu/Temp/stm.dat");

        if(!file.exists()){// 파일이 존재하지 않을 경우도 있으니 예외처리 
            return;
        }

        try (FileInputStream fis = new FileInputStream(file); // 클로즈하려고 finally쪽에 넣어줌 
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis)
        ){
            //헤더 읽기 ===================================================================
            //시그니처확인
            if(dis.readByte()!='S' || dis.readByte()!='T' || dis.readByte()!='M') {
                IoManager.print("지원되지 않는 파일이거나, 파일이 깨졌습니다.");                 
            }

            //카운트 확인 (이게 있어야 몇번 읽어야하는지 알 수 있음) 
            int count = dis.readInt();
            //헤더 끝 ===================================================================

            for(int i = 0; i < count ; i++){
                String name = dis.readUTF();
                int age =dis.readInt();
                int score =dis.readInt();
                list.add(new StudentDto(name,age,score));
            }
            
            IoManager.print("파일이 정상적으로 로드되었습니다.");
                
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
        
        


