import java.util.Scanner;
import model.StudentManager;
import persistence.StudentData;
import menu.MenuBuilder;
import menu.MenuGroup;
import ui.ConsoleUI;

/**
 * App 클래스 (메인 클래스)
 * 
 * 학생 관리 프로그램의 진입점입니다.
 * 
 * 책임:
 * - 프로그램 초기화 (StudentManager 생성, 데이터 로드)
 * - 메뉴 시스템 구성
 * - 메뉴 실행
 * - 프로그램 종료 처리
 * 
 * 아키텍처:
 * - Composite Pattern: 메뉴 시스템 (Menu, MenuItem, MenuGroup)
 * - Command Pattern: 각 메뉴 동작 (MenuCommand와 구현체)
 * - SRP: 각 클래스는 단일 책임만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class App {
    
    /**
     * 프로그램의 메인 메서드입니다.
     * 
     * 실행 순서:
     * 1. 시작 화면 표시
     * 2. StudentManager 생성
     * 3. 기존 데이터 로드
     * 4. 메뉴 시스템 구성
     * 5. 메뉴 실행
     * 6. 종료 시 자동 저장
     * 7. 종료 화면 표시
     * 
     * @param args 커맨드 라인 인자 (현재 사용 안함)
     */
    public static void main(String[] args) {
        // ===== 시작 화면 =====
        ConsoleUI.printWelcome();
        
        // ===== 초기화 =====
        // StudentManager 생성
        StudentManager manager = new StudentManager();
        
        // Scanner 생성 (사용자 입력)
        Scanner scanner = new Scanner(System.in);
        
        // 프로그램 시작 시 기존 데이터 로드
        manager.setStudents(StudentData.loadStudents());
        
        // ===== 메뉴 구성 =====
        MenuGroup mainMenu = MenuBuilder.buildMainMenu(manager, scanner);
        
        // ===== 메뉴 실행 =====
        mainMenu.execute();
        
        // ===== 종료 처리 =====
        System.out.println("\n프로그램 종료 전 데이터를 자동 저장합니다.");
        StudentData.saveStudents(manager);
        
        ConsoleUI.printGoodbye();
        
        // Scanner 자원 해제
        scanner.close();
    }
}
