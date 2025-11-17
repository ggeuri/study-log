package repository;

import java.util.ArrayList;
import java.util.List;

import dto.StudentDto;

public class Repository {
    StudentDto studentDto = new StudentDto();
    List<StudentDto> list = new ArrayList<>(); 

    public void addStudent(String name, int age, int score){
        studentDto.setName(name);
        studentDto.setAge(age);
        studentDto.setScore(score);

        list.add(studentDto);
    }

    public void searchStudent(String searchKey){
        for(int i = 0 ; i < list.size(); i++){
        if(list.get(i).getName().contains(searchKey)){//수정피룡
            System.out.println(list.get(i).getName());
            System.out.println(list.get(i).getAge());
            System.out.println(list.get(i).getScore());
        }

    }


    }


}
