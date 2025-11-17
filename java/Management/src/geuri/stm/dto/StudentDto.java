package geuri.stm.dto;

public class StudentDto {
    private String name ; 
    private int age ; 
    private int score; 

    public StudentDto(String name, int age, int score) {
        this.name = name; 
        this.age = age; 
        this.score = score;
    }

    StudentDto(){}

    public void setName(String name){
        this.name = name; 
    }

    public String getName(){
        return name;
    }
    public void setAge(int age){
        this.age = age; 
    }

    public int getAge(){
        return age;
    }
    public void setScore(int score){
        this.score = score; 
    }

    public int getScore(){
        return score;
    }

}
