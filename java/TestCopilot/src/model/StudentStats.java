package model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * StudentStats 클래스
 * 
 * 학생 성적에 대한 통계 정보를 계산하고 제공합니다.
 * 평균, 최고점, 최저점, 점수 분포 등의 정보를 제공합니다.
 * 
 * SRP 원칙: 통계 계산만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class StudentStats {
    
    private List<Student> students;
    
    /**
     * StudentStats 생성자
     * 
     * @param students 분석할 학생 리스트
     */
    public StudentStats(List<Student> students) {
        this.students = students;
    }
    
    /**
     * 모든 학생의 평균 점수를 반환합니다.
     * 
     * @return 평균 점수, 학생이 없으면 0.0
     */
    public double getAverageScore() {
        if (students.isEmpty()) {
            return 0.0;
        }
        return students.stream()
                .mapToDouble(Student::getScore)
                .average()
                .orElse(0.0);
    }
    
    /**
     * 최고 점수를 반환합니다.
     * 
     * @return 최고 점수, 학생이 없으면 0.0
     */
    public double getMaxScore() {
        if (students.isEmpty()) {
            return 0.0;
        }
        return students.stream()
                .mapToDouble(Student::getScore)
                .max()
                .orElse(0.0);
    }
    
    /**
     * 최저 점수를 반환합니다.
     * 
     * @return 최저 점수, 학생이 없으면 0.0
     */
    public double getMinScore() {
        if (students.isEmpty()) {
            return 0.0;
        }
        return students.stream()
                .mapToDouble(Student::getScore)
                .min()
                .orElse(0.0);
    }
    
    /**
     * 평균 나이를 반환합니다.
     * 
     * @return 평균 나이, 학생이 없으면 0.0
     */
    public double getAverageAge() {
        if (students.isEmpty()) {
            return 0.0;
        }
        return students.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }
    
    /**
     * 특정 점수 범위의 학생들을 반환합니다.
     * 
     * @param minScore 최소 점수 (포함)
     * @param maxScore 최대 점수 (포함)
     * @return 범위 내의 학생 리스트
     */
    public List<Student> getStudentsByScoreRange(double minScore, double maxScore) {
        return students.stream()
                .filter(s -> s.getScore() >= minScore && s.getScore() <= maxScore)
                .collect(Collectors.toList());
    }
    
    /**
     * 우수 학생(점수 80점 이상)을 반환합니다.
     * 
     * @return 우수 학생 리스트
     */
    public List<Student> getTopStudents() {
        return getStudentsByScoreRange(80, 100);
    }
    
    /**
     * 점수 분포를 반환합니다.
     * 
     * @return [90점 이상, 80점 이상, 70점 이상, 60점 이상, 60점 미만]의 개수
     */
    public int[] getScoreDistribution() {
        int[] distribution = new int[5];
        
        for (Student student : students) {
            double score = student.getScore();
            if (score >= 90) distribution[0]++;
            else if (score >= 80) distribution[1]++;
            else if (score >= 70) distribution[2]++;
            else if (score >= 60) distribution[3]++;
            else distribution[4]++;
        }
        
        return distribution;
    }
}
