package p10;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //Case 설명 ===================================================
        //여기가 Service 내부 코드라는 가정
        // Repository에서 getList() 호출했다. 
        // 근데 Repository에서는 StudentEntity로 결과 줌 
        // 다시 Controller로 배열을 리턴해야하는데 StudentDto 배열로 리턴해야한다 
        // DB(Repository)에서 Entity 리스트로 주고 -> 서비스에서 Dto로 변환해서 Controller로 전달하고 ... 레이어별로 Dto가 다름...
        
        List<StudentEntity> entitieList = new ArrayList<>();
        entitieList.add(new StudentEntity("한조", 30, 99));
        entitieList.add(new StudentEntity("철수", 30, 99));
        entitieList.add(new StudentEntity("영희", 30, 99));
        entitieList.add(new StudentEntity("길동", 30, 99));
        entitieList.add(new StudentEntity("한...", 30, 99));

        
        // for (StudentEntity entity : entitieList) {
        //     if(entity.getName().startsWith("한")){
        //         dtoList.add(entity.toDto());
        //     }
        // }
        
        double ds = entitieList.stream()
        .filter(e -> e.getScore()>=50)
        .mapToDouble(e -> e.getScore())
        .average()
        .getAsDouble();
        
        List<StudentDto> dtoList = new ArrayList<>(); 
        
        dtoList.stream()
            .map(a -> a.getScore())
            .distinct()
            .sorted()
            .forEach(System.out::println);

        dtoList.stream()
            .map(a -> a.getName()+a.getScore())
            .forEach(System.out::println);

// ①
// 이름 길이가 3자 이하인 학생만 필터링
// .filter() 사용

        dtoList.stream()
            .filter(a -> a.getName().length() <= 3)
            // .map(StudentDto::getName) 위아래 같은거임
            // .map(a -> a.getName())
            .forEach(System.out::println);
        
        for (StudentDto studentDto : dtoList) {
            if(studentDto.getName().length() <= 3){
                System.out.println(studentDto.getName());
            }
            
        }

// ②
// 모든 학생 이름 대문자로 변환
// .map(dto -> dto.getName().toUpperCase())
        dtoList.stream()
            .map(a -> a.getName().toUpperCase())
            .forEach(System.out::println);

        for (StudentDto studentDto : dtoList) {
            String name = studentDto.getName().toUpperCase();
            System.out.println(name);
        }
// ③
// 점수 내림차순 정렬 후 이름만 출력
// .sorted((a,b)->b.getScore()-a.getScore())

        dtoList.stream()
            .sorted((a,b) -> b.getScore() - a.getScore())
            .map(a -> a.getName())
            .forEach(System.out::println);


        StudentDto[] scoreList = new StudentDto[dtoList.size()];
            for(int i = 0 ; i < scoreList.length; i++){
                scoreList[i].setScore(studentDto.getScore());
                if(scoreList[0].getScore()>=scoreList[i].getScore()){
                    //이거너무과한데..
                }
            
        }

// ④
// 평균 점수보다 높은 학생 이름 출력
// .mapToInt().average() 활용
        double average = dtoList.stream()
        .mapToInt(a -> a.getScore())  
        .average()                    
        .orElse(0);  

        dtoList.stream()
        .filter(a -> a.getScore() > average)
        .map(a -> a.getName())
        .forEach(System.out::println);

        int sum = 0; 

        for (StudentDto studentDto : dtoList) {
            sum += studentDto.getScore();
        }

        double avg = sum / (double)dtoList.size();

        for (StudentDto studentDto : dtoList) {
            if(studentDto.getScore()>=avg){
                System.out.println(studentDto.getName());
            }

        }

// ⑤
// 나이가 20 이상인 학생 중 “김”씨만 Dto 변환
// .filter().map().toList()

        

        // 위처럼 Repository가 배열을 Service에 전달 
        // 문제는 StudentEntity배열을 -> StudentDto 배열로 변환해라 

        
        // for(StudentEntity entity : entitieList) {
        //     StudentDto studentDto = new StudentDto(
        //         entity.getName(),
        //         entity.getAge(),
        //         entity.getScore());
        //         dtoList.add(studentDto);
        // }
        // for(StudentEntity entity : entitieList) {
        //     if(entity.getName().startsWith("김")){
        //     StudentDto studentDto = entity.toDto();
        //     // StudentDto studentDto = StudentDto.fromEntity(entity);택 1 . 정적 팩토리 
        //     dtoList.add(studentDto);
        //     }
        // }

        // stream API는 람다식이 공식 .펑션,컨슈머, 프리디케이트 서플라이어 봤을때 어떻게 구현해야하는지 알아야함 
        // entitieList.stream().map(entity -> {
        //     return entity.toDto(); // 둘 중 하나 택일 
        //     // return studentDto.fromEntity(); 
        // });//실무에서 제일 많이쓰는 것 : entitieList 반복문 돌리겠다   

        //  List<StudentDto> dtoList = entitieList.stream()
        //     // .filter(entity -> entity.getName().startsWith("김"))
        //     .map(entity -> entity.toDto())
        //     .toList(); 

        //     double average = dtoList.stream()
        //         .filter(dto -> dto.getName().length() <= 3)
        //         .mapToInt(dto -> dto.getScore())
        //         .filter(score -> score >= 50)
        //         .distinct()
        //         .average()
        //         .getAsDouble();

            // 둘 중 하나 택일 
            // return studentDto.fromEntity(); );//실무에서 제일 많이쓰는 것 : entitieList 반복문 돌리겠다   

    }

}

// Controller - Service 간에 사용될 구조
class StudentDto {
    private String name;
    private int age; 
    private int score; 

    public StudentDto(String name, int age, int score){
        this.name = name; 
        this.age = age; 
        this.score = score; 
    }

        public static StudentDto fromEntity(StudentEntity entity){//보통 여기서 static 선호 
            return new StudentDto(entity.getName(),entity.getAge(),entity.getScore());
        }

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

// Service - Repository 간에 사용될 구조 
class StudentEntity { 
    private String name;
    private int age; 
    private int score; 
    
    public StudentEntity(String name, int age, int score){
        this.name = name; 
        this.age = age; 
        this.score = score; 
    }

    public StudentDto toDto() {//변환용 메서드 : Entity를 Dto로 변환 
        return new StudentDto(name, age, score);

    }
    
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
