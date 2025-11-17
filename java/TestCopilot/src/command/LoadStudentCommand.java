package command;

import java.util.Scanner;
import model.StudentManager;
import persistence.StudentData;

/**
 * LoadStudentCommand 클래스
 * 
 * 파일에서 학생 정보를 로드하는 커맨드 클래스입니다.
 * 
 * SRP 원칙: 파일 로드 기능만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class LoadStudentCommand implements menu.MenuCommand {
    
    private StudentManager manager;
    private Scanner scanner;

    public LoadStudentCommand(StudentManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println("\n⚠ 경고: 현재 메모리의 모든 데이터가 제거되고 파일의 데이터로 대체됩니다.");
        System.out.print("계속하시겠습니까? (y/n): ");
        
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("y") || confirm.equals("yes")) {
            manager.setStudents(StudentData.loadStudents());
            System.out.println("✓ 로드 완료!\n");
            return true;
        } else {
            System.out.println("로드가 취소되었습니다.\n");
            return false;
        }
    }
}
