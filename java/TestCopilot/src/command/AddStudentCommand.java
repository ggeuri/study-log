package command;

import java.util.Scanner;
import model.Student;
import model.StudentManager;
import model.StudentValidator;
import ui.ConsoleUI;

/**
 * AddStudentCommand 클래스
 * 
 * 학생 등록 기능을 수행하는 커맨드 클래스입니다.
 * MenuCommand 인터페이스를 구현하여 메뉴 시스템과 통합됩니다.
 * 
 * SRP 원칙:
 * - 학생 등록 기능만 담당
 * - 사용자 입력 유효성 검사는 StudentValidator에 위임
 * - UI 표현은 ConsoleUI에 위임
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class AddStudentCommand implements menu.MenuCommand {
    
    // ==================== 멤버 변수 ====================
    
    /** 학생을 관리하는 StudentManager */
    private StudentManager manager;
    
    /** 사용자 입력을 받기 위한 Scanner */
    private Scanner scanner;

    
    // ==================== 생성자 ====================
    
    /**
     * AddStudentCommand 생성자
     * 
     * @param manager 학생을 관리할 StudentManager
     * @param scanner 사용자 입력을 받을 Scanner
     */
    public AddStudentCommand(StudentManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    
    // ==================== MenuCommand 인터페이스 구현 ====================
    
    /**
     * 학생 등록 명령을 실행합니다.
     * 
     * @return 등록 성공 여부
     */
    @Override
    public boolean execute() {
        ConsoleUI.printMenuTitle("➕ 새 학생 등록");
        
        // 이름 입력
        System.out.print("\n이름: ");
        String name = scanner.nextLine().trim();

        String nameError = StudentValidator.getNameErrorMessage(name);
        if (nameError != null) {
            ConsoleUI.printError(nameError);
            return false;
        }

        // 나이 입력
        System.out.print("나이: ");
        String ageStr = scanner.nextLine().trim();
        
        String ageError = StudentValidator.getAgeErrorMessage(ageStr);
        if (ageError != null) {
            ConsoleUI.printError(ageError);
            return false;
        }
        
        int age = Integer.parseInt(ageStr);

        // 점수 입력
        System.out.print("점수: ");
        String scoreStr = scanner.nextLine().trim();
        
        String scoreError = StudentValidator.getScoreErrorMessage(scoreStr);
        if (scoreError != null) {
            ConsoleUI.printError(scoreError);
            return false;
        }
        
        double score = Double.parseDouble(scoreStr);

        // 모든 입력값이 유효하므로 새로운 학생 등록
        manager.addStudent(new Student(name, age, score));
        ConsoleUI.printSuccess(name + " 학생이 등록되었습니다.");
        System.out.println();
        
        return true;
    }
}
