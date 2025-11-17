package command;

import java.util.Scanner;
import model.StudentManager;

/**
 * UpdateStudentCommand 클래스
 * 
 * 학생 정보 수정 기능을 수행하는 커맨드 클래스입니다.
 * 
 * SRP 원칙: 학생 수정 기능만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class UpdateStudentCommand implements menu.MenuCommand {
    
    private StudentManager manager;
    private Scanner scanner;

    public UpdateStudentCommand(StudentManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println("\n========== 학생 수정 ==========");
        System.out.print("수정할 학생의 이름: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("✗ 이름은 비워두실 수 없습니다.\n");
            return false;
        }

        System.out.print("새로운 나이: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
            if (age < 0 || age > 150) {
                System.out.println("✗ 올바른 나이를 입력해주세요 (0~150).\n");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ 나이는 숫자여야 합니다.\n");
            return false;
        }

        System.out.print("새로운 점수: ");
        double score;
        try {
            score = Double.parseDouble(scanner.nextLine().trim());
            if (score < 0 || score > 100) {
                System.out.println("✗ 올바른 점수를 입력해주세요 (0~100).\n");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ 점수는 숫자여야 합니다.\n");
            return false;
        }

        manager.updateStudent(name, age, score);
        System.out.println();
        return true;
    }
}
