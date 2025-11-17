package command;

import java.util.Scanner;
import model.StudentManager;

/**
 * DeleteStudentCommand 클래스
 * 
 * 학생 삭제 기능을 수행하는 커맨드 클래스입니다.
 * 
 * SRP 원칙: 학생 삭제 기능만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class DeleteStudentCommand implements menu.MenuCommand {
    
    private StudentManager manager;
    private Scanner scanner;

    public DeleteStudentCommand(StudentManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println("\n========== 학생 삭제 ==========");
        System.out.print("삭제할 학생의 이름: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("✗ 이름은 비워두실 수 없습니다.\n");
            return false;
        }

        manager.deleteStudent(name);
        System.out.println();
        return true;
    }
}
