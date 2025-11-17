package model;

/**
 * Student 클래스
 * 
 * 학생의 기본 정보(이름, 나이, 점수)를 저장하고 관리하는 클래스입니다.
 * 학생 개체의 데이터를 캡슐화하고 getter/setter를 통해 안전하게 접근할 수 있습니다.
 * 
 * @author 학생관리프로그램
 * @version 1.0
 */
public class Student {
    
    // ==================== 멤버 변수 ====================
    
    /** 학생의 이름 */
    private String name;
    
    /** 학생의 나이 */
    private int age;
    
    /** 학생의 점수 (0.0 ~ 100.0) */
    private double score;

    
    // ==================== 생성자 ====================
    
    /**
     * Student 생성자
     * 
     * 이름, 나이, 점수를 받아서 새로운 학생 객체를 생성합니다.
     * 
     * @param name 학생의 이름
     * @param age 학생의 나이
     * @param score 학생의 점수
     */
    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    
    // ==================== Getter 메서드 ====================
    
    /**
     * 학생의 이름을 반환합니다.
     * 
     * @return 학생의 이름
     */
    public String getName() {
        return name;
    }

    /**
     * 학생의 나이를 반환합니다.
     * 
     * @return 학생의 나이
     */
    public int getAge() {
        return age;
    }

    /**
     * 학생의 점수를 반환합니다.
     * 
     * @return 학생의 점수
     */
    public double getScore() {
        return score;
    }

    
    // ==================== Setter 메서드 ====================
    
    /**
     * 학생의 이름을 설정합니다.
     * 
     * @param name 설정할 새로운 이름
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 학생의 나이를 설정합니다.
     * 
     * @param age 설정할 새로운 나이
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 학생의 점수를 설정합니다.
     * 
     * @param score 설정할 새로운 점수
     */
    public void setScore(double score) {
        this.score = score;
    }

    
    // ==================== 기타 메서드 ====================
    
    /**
     * 학생의 정보를 문자열 형태로 반환합니다.
     * 
     * 형식: "이름: 홍길동, 나이: 25, 점수: 95.50"
     * 
     * @return 학생의 정보가 담긴 문자열
     */
    @Override
    public String toString() {
        return String.format("이름: %s, 나이: %d, 점수: %.2f", name, age, score);
    }
}
