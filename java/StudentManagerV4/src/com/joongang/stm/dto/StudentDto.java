package com.joongang.stm.dto;
//DTO = 기능 없음. 자료 형태를 묶기 위한 클래스. 핵심은 속성 
public class StudentDto {
    private String name; // private 니까 나중에 set-get  
    private int age; 
    private int score; 

    // All Argument Constructor. 나중에 Spring배우면 All이 아닌 No Argument Constructor
    // All이라 그냥 생성못함 -> 생성자 오버로딩으로 No Argument Constructor 같이 만들어줌
    public StudentDto(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score; // 그냥 공식이나 다름없는 코드
    }
    //No Argument Constructor(요즘은 이게 기본.)
    public StudentDto(){}

    // setter, getter : 속성 접근을 메서드로, 문법은 메서드지만 개념적으로 기능으로 분류안함. 기능없으니
    public void setName(String name){
        this.name = name ; 
    }
    public void setAge(int age){
        this.age = age ; 
    }
    public void setScore(int score){
        this.score = score ; 
    }

    public String getName() {
        return name; 
    }
    public int getAge(){
        return age;
    }
    public int getScore(){
        return score;
    }
}
