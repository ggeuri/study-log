package com.joongang.stm.service;

import java.util.List;

import com.joongang.stm.dto.StudentDto;
import com.joongang.stm.repository.Repository;
import com.joongang.stm.util.IoManager;

//컴포넌트
//역할:비즈니스로직(코어로직)담당 
public class Service {
    private Repository repository = new Repository(); 


    public void addStudent(){
        IoManager.print("========학생 정보 등록========");
        String name = IoManager.input("이름 입력 > ");
        int age = Integer.parseInt(IoManager.input("나이 입력 > "));
        int score = Integer.parseInt(IoManager.input("점수 입력 > "));

        StudentDto studentDto = new StudentDto(name,age,score);
        repository.save(studentDto);

        IoManager.print("==========================");
    }

    public void listStudent() {
        IoManager.print("========학생 정보 출력========");

        List<StudentDto> list = repository.findAll();

        for (StudentDto studentDto : list) {
            IoManager.print("이름 : " + studentDto.getName() + ", 나이: " + studentDto.getAge() + ", 점수: " + studentDto.getScore());
        }

        IoManager.print("총 인원: " + list.size());

        IoManager.print("==========================");
    }

    public void searchStudent(){
        IoManager.print("========학생 정보 찾기========");
        String searchWord = IoManager.input("검색어 입력 > ");

        List<StudentDto> list = repository.findByNameContaining(searchWord);

        for (StudentDto studentDto : list) {
            IoManager.print("이름 : " + studentDto.getName() + ", 나이: " + studentDto.getAge() + ", 점수: " + studentDto.getScore());
        }

        IoManager.print("검색된 인원: " + list.size());

        IoManager.print("==========================");

    }

    public void removeStudent(){
        IoManager.print("========학생 정보 삭제========");
        String removeName = IoManager.input("검색어 입력 > ");

        int deleteCount = repository.removeByname(removeName);

        IoManager.print("삭제된 인원: " + deleteCount);

        IoManager.print("==========================");
    }




   
    
}
