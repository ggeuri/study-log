package model;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentManager 클래스
 * 
 * 학생 정보를 관리하는 비즈니스 로직을 담당하는 클래스입니다.
 * 학생 등록, 삭제, 수정, 검색 및 목록 조회 기능을 제공합니다.
 * ArrayList를 사용하여 여러 학생 정보를 메모리에 저장합니다.
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class StudentManager {
    
    // ==================== 멤버 변수 ====================
    
    /** 학생 정보를 저장하는 ArrayList 컬렉션 */
    private List<Student> students;

    
    // ==================== 생성자 ====================
    
    /**
     * StudentManager 생성자
     * 
     * 학생 정보를 저장할 빈 ArrayList를 초기화합니다.
     */
    public StudentManager() {
        this.students = new ArrayList<>();
    }

    
    // ==================== 학생 추가 메서드 ====================
    
    /**
     * 새로운 학생을 등록합니다.
     * 
     * 제공된 Student 객체를 ArrayList에 추가하고
     * 등록 완료 메시지를 출력합니다.
     * 
     * @param student 등록할 학생 객체
     */
    public void addStudent(Student student) {
        // ArrayList에 새로운 학생 객체를 추가
        students.add(student);
        // 사용자에게 등록 완료를 알림
        System.out.println("✓ 학생이 등록되었습니다.");
    }

    
    // ==================== 학생 삭제 메서드 ====================
    
    /**
     * 이름으로 학생을 검색하여 삭제합니다.
     * 
     * ArrayList를 순회하면서 입력받은 이름과 일치하는 학생을 찾아
     * 삭제합니다. 삭제 성공 여부를 반환합니다.
     * 
     * @param name 삭제할 학생의 이름
     * @return 삭제 성공 시 true, 실패 시 false
     */
    public boolean deleteStudent(String name) {
        // ArrayList의 모든 학생을 순회
        for (int i = 0; i < students.size(); i++) {
            // 현재 학생의 이름과 입력받은 이름을 비교
            if (students.get(i).getName().equals(name)) {
                // 일치하는 학생을 ArrayList에서 제거
                students.remove(i);
                // 사용자에게 삭제 완료를 알림
                System.out.println("✓ 학생이 삭제되었습니다.");
                // 삭제 성공 반환
                return true;
            }
        }
        // 일치하는 학생을 찾지 못한 경우 메시지 출력
        System.out.println("✗ 해당하는 학생을 찾을 수 없습니다.");
        // 삭제 실패 반환
        return false;
    }

    
    // ==================== 학생 정보 수정 메서드 ====================
    
    /**
     * 이름으로 학생을 검색하여 나이와 점수를 수정합니다.
     * 
     * ArrayList를 순회하면서 입력받은 이름과 일치하는 학생을 찾아
     * 나이와 점수를 새로운 값으로 업데이트합니다.
     * 
     * @param name 수정할 학생의 이름
     * @param newAge 새로운 나이
     * @param newScore 새로운 점수
     * @return 수정 성공 시 true, 실패 시 false
     */
    public boolean updateStudent(String name, int newAge, double newScore) {
        // 향상된 for 루프를 사용하여 ArrayList의 모든 학생을 순회
        for (Student student : students) {
            // 현재 학생의 이름과 입력받은 이름을 비교
            if (student.getName().equals(name)) {
                // 학생의 나이를 새로운 값으로 변경
                student.setAge(newAge);
                // 학생의 점수를 새로운 값으로 변경
                student.setScore(newScore);
                // 사용자에게 수정 완료를 알림
                System.out.println("✓ 학생 정보가 수정되었습니다.");
                // 수정 성공 반환
                return true;
            }
        }
        // 일치하는 학생을 찾지 못한 경우 메시지 출력
        System.out.println("✗ 해당하는 학생을 찾을 수 없습니다.");
        // 수정 실패 반환
        return false;
    }

    
    // ==================== 모든 학생 목록 조회 메서드 ====================
    
    /**
     * 현재 등록되어 있는 모든 학생의 목록을 화면에 출력합니다.
     * 
     * 등록된 학생이 없을 경우 안내 메시지를 출력합니다.
     * 등록된 학생이 있을 경우 번호와 함께 학생 정보를 출력합니다.
     */
    public void displayAllStudents() {
        // ArrayList가 비어있는지 확인 (학생이 없는 경우)
        if (students.isEmpty()) {
            // 빈 리스트에 대한 안내 메시지 출력
            System.out.println("등록된 학생이 없습니다.");
            return;
        }

        // 표 형태의 헤더 출력
        System.out.println("\n========== 학생 목록 ==========");
        
        // ArrayList의 모든 학생 정보를 번호와 함께 출력
        for (int i = 0; i < students.size(); i++) {
            // (번호) 형태로 학생 정보를 출력 (번호는 1부터 시작)
            System.out.println((i + 1) + ". " + students.get(i));
        }
        
        // 표 형태의 푸터 출력
        System.out.println("==============================\n");
    }

    
    // ==================== 학생 검색 메서드 ====================
    
    /**
     * 이름으로 특정 학생을 검색하여 정보를 출력합니다.
     * 
     * ArrayList를 순회하면서 입력받은 이름과 일치하는 학생을 찾아
     * 해당 학생의 정보를 출력합니다.
     * 
     * @param name 검색할 학생의 이름
     */
    public void searchStudent(String name) {
        // 향상된 for 루프를 사용하여 ArrayList의 모든 학생을 순회
        for (Student student : students) {
            // 현재 학생의 이름과 입력받은 이름을 비교
            if (student.getName().equals(name)) {
                // 일치하는 학생의 정보를 출력
                System.out.println("\n찾은 학생: " + student + "\n");
                return; // 메서드 종료
            }
        }
        // 일치하는 학생을 찾지 못한 경우 메시지 출력
        System.out.println("✗ 해당하는 학생을 찾을 수 없습니다.");
    }

    
    // ==================== 학생 개수 조회 메서드 ====================
    
    /**
     * 현재 등록되어 있는 학생의 총 개수를 반환합니다.
     * 
     * @return ArrayList에 저장된 학생의 개수
     */
    public int getStudentCount() {
        // 현재 ArrayList에 저장된 학생의 개수 반환
        return students.size();
    }

    
    // ==================== 파일 I/O 지원 메서드 ====================
    
    /**
     * 현재 관리하는 모든 학생 정보의 리스트를 반환합니다.
     * 
     * StudentData 클래스에서 파일 저장 시 사용하기 위해
     * 내부 students 리스트에 접근할 수 있도록 제공합니다.
     * 
     * @return 모든 학생을 포함하는 List<Student>
     */
    public List<Student> getAllStudents() {
        // 내부적으로 관리하는 전체 학생 리스트 반환
        return students;
    }

    /**
     * 파일에서 로드한 학생 정보로 현재 리스트를 초기화합니다.
     * 
     * 프로그램 시작 시 저장된 파일의 데이터를 불러올 때 사용합니다.
     * 기존 데이터는 모두 제거되고 새로운 데이터로 대체됩니다.
     * 
     * @param loadedStudents 파일에서 로드한 학생 리스트
     */
    public void setStudents(List<Student> loadedStudents) {
        // 기존 학생 정보를 모두 제거
        this.students.clear();
        
        // 로드된 학생 정보를 모두 추가
        this.students.addAll(loadedStudents);
    }
}
