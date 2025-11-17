package p2;

public class App {
    public static void main(String[] args) {
        StudentDto s1 = StudentDto.builder()
            .name("한조") // 생성패턴, 불변 
            .age(30)
            .score(99)
            .build();
    
    }

}


class StudentDto{
    private String name;
    private int age;
    private int score;

    private StudentDto(){}

    private StudentDto(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.score = builder.score;

    }
    //Builder Pattern (여러 디자인 패턴 중 하나)
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String name;
        private int age;
        private int score;
    
        private Builder(){}
    
        public Builder name(String name){
            this.name = name; 
            return this;
        }
        public Builder age(int age){
            this.age = age; 
            return this;
        }
        public Builder score(int score){
            this.score = score; 
            return this;
        }

        public StudentDto build(){
            return new StudentDto(this);
        }

    }

}