package p2;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        StudentDto s1 = new StudentDto();
        StudentDto s2 = new StudentDto();
        School school1 = new School();


        List<StudentDto> studentDtos = new ArrayList<>();
        studentDtos.add(s1);
        studentDtos.add(s2);

        List<School> schools = new ArrayList<>();
        schools.add(school1);


        
    }

}

class StudentDto{
    String name;
    int age;
    int score;
}

class School {
    String name; 

}
