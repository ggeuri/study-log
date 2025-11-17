package geuri.stm.repository;

import geuri.stm.dto.StudentDto;

public class Repository {//저장하고 값을 반환해주는 것까지만 

    private StudentDto[] list = new StudentDto[5];
    private int count = 0 ; 

    public void save(StudentDto studentDto) {
        list[count] = studentDto;
        count++;
    }

    public StudentDto[] find(){ //주소값을 반환해서 

        StudentDto[] newList = new StudentDto[count];

        for(int i = 0; i < count; i++) {
            String name = list[i].getName();
            int age = list[i].getAge(); 
            int score = list[i].getScore();
            
            StudentDto studentDto = new StudentDto(name, age, score);
            newList[i] = studentDto;
        }
        return newList; 
    }

    public StudentDto[] findByName(String searchWord){
        int searchCount = 0; 
        for(int i = 0; i < count; i++) {
            if(list[i].getName().contains(searchWord)){
                searchCount++;}
            }

        StudentDto[] newList = new StudentDto[searchCount]; 
        int newListCount = 0; 
        for(int i = 0; i < count; i++) {
            if(list[i].getName().contains(searchWord)){
                String name = list[i].getName();
                int age = list[i].getAge();
                int score = list[i].getScore();
                StudentDto studentDto = new StudentDto(name, age, score);
                
                newList[newListCount] = studentDto;
                newListCount++;
            }

        } 
        return newList;  
    }

    public int deleteStudent(String deleteName){
        int deleteCount = 0; 
        
        for(int i = 0; i < count; i++) {
            if(list[i].getName().equals(deleteName)){
                for(int x = i; x < count -1 ; x++){
                    list[x] = list[x+1];
                }
                deleteCount++;
                i--;
                count--;
                }
            }

        return deleteCount; 
    }

    public StudentDto[] updateStudent(String targetName, String updateName){

        int updateCount = 0; 
        for(int i = 0; i < count; i++) {
            if(list[i].getName().equals(targetName)){
                updateCount++;}
            }

        StudentDto[] newList = new StudentDto[updateCount];

        for(int i = 0; i < count ; i++){
            if(targetName.equals(list[i].getName())){
                list[i].setName(updateName);

                String name = list[i].getName();
                int age = list[i].getAge();
                int score = list[i].getScore();

                StudentDto studentDto = new StudentDto(name, age, score);

                newList[i] = studentDto;

            }
        }

        return newList;

    }

    public StudentDto[] updateStudentAge(String targetName, int updateAge){

        int updateCount = 0; 
        for(int i = 0; i < count; i++) {
            if(list[i].getName().equals(targetName)){
                updateCount++;}
            }

        StudentDto[] newList = new StudentDto[updateCount];

        for(int i = 0; i < count ; i++){
            if(targetName.equals(list[i].getName())){
                list[i].setAge(updateAge);

                String name = list[i].getName();
                int age = list[i].getAge();
                int score = list[i].getScore();

                StudentDto studentDto = new StudentDto(name, age, score);

                newList[i] = studentDto;

            }
        }

        return newList;

    }
    public StudentDto[] updateStudentScore(String targetName, int updateScore){

        int updateCount = 0; 
        for(int i = 0; i < count; i++) {
            if(list[i].getName().equals(targetName)){
                updateCount++;}
            }

        StudentDto[] newList = new StudentDto[updateCount];

        for(int i = 0; i < count ; i++){
            if(targetName.equals(list[i].getName())){
                list[i].setScore(updateScore);

                String name = list[i].getName();
                int age = list[i].getAge();
                int score = list[i].getScore();

                StudentDto studentDto = new StudentDto(name, age, score);

                newList[i] = studentDto;

            }
        }

        return newList;

    }

    public int calculateScore(){

        int sumScore = 0;

        for(int i = 0; i < count ; i++){
            sumScore += list[i].getScore();
        }

        return sumScore;

    }



    






}

