package dto;


public class StudentDto {
    private String name ; 
    private int age ; 
    private int score ; 

    public StudentDto(){}

    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setScore(int score){
        this.score = score;
    }

    public String getName(){
        return name; 
    }

    public int getAge(){
        return age;
    }
    public int getScore(){
        return score;
    }

}
