package com.joongang.stm.repository;

import com.joongang.stm.dto.StudentDto;

//컴포넌트
//역할 : 저장소 - 데이터 입출력 담당 
//이부분은 사실상 DB쿼리로 변경될 것 . 직접 구현할 일 없음
// 만약 전체가 부담되면 Repository는 복사해서 시작해도됨 .
public class Repository {

    private StudentDto[] list = new StudentDto[5]; // 멤버: 모든 메서드가 얘를 쓸거다 
    private int count = 0 ;

    public void save(StudentDto studentDto){
        list[count] = studentDto;
        count++;
    }

    public StudentDto[] findAll(){

        //깊은 복사
        // 코드 복습시 혹시 아래 부분 조금 어렵다고 느껴지면
        // 필요한만큼 배열복사해서 리턴한다.
        StudentDto[] newList = new StudentDto[count];

        for(int i = 0 ; i < count ; i ++) {
            String name = list[i].getName();
            int age = list[i].getAge();
            int score = list[i].getScore();

            StudentDto studentDto = new StudentDto(name,age,score);
            newList[i] = studentDto;
        }

        return newList;
    }

    public StudentDto[] findByNameContaining(String serachWord){
        int searchCount = 0 ; 
        for(int i = 0; i < count; i++) {
            if(list[i].getName().contains(serachWord)){
                searchCount++;
            }
        }
        
        StudentDto[] newList = new StudentDto[searchCount];
        int newListCount = 0;
        for(int i = 0; i < count; i++) {
            if(list[i].getName().contains(serachWord)){
                String name = list[i].getName();
                int age = list[i].getAge();
                int score = list[i].getScore();
                StudentDto studentDto = new StudentDto(name,age,score);
                newList[newListCount] = studentDto; 
            }
        }
        return newList;
    }

    public int deleteByName(String deleteName){
        int deleteCount = 0; 

        for(int i = 0 ; i < count; i++) {
            if(list[i].getName().equals(deleteName)){
                for(int x = i ; x < count -1; x++ ) {
                    list[x] = list[x+1];
                }
                count--;
                i--; 
                deleteCount++;
            }
        }

        return deleteCount ; 
    }

}
