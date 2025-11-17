package p4;

public class App {
    public static void main(String[] args) {
        Student student = new Student("홍길동",20,95);
        student.introduce();

    }

}


class Student {
    private String name;
    private int age;
    private int score;

    public Student(){}
    public Student(String name, int age, int score){
        this.name = name;
        this.age = age;
        this.score = score;
    }

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





    public void introduce(){
        System.out.println("이름: " + name + ", 나이: " + age + ", 점수: " + score);
    }


}
