package com.joongang.stm.repository;

import java.util.ArrayList;
import java.util.List;

import com.joongang.stm.dto.StudentDto;

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



}
