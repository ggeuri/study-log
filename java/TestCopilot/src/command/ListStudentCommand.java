package command;

import model.Student;
import model.StudentManager;
import ui.ConsoleUI;

/**
 * ListStudentCommand 클래스
 * 
 * 학생 목록 조회 기능을 수행하는 커맨드 클래스입니다.
 * 테이블 형식으로 예쁘게 표시합니다.
 * 
 * SRP 원칙: 학생 목록 조회 기능만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class ListStudentCommand implements menu.MenuCommand {
    
    private StudentManager manager;

    public ListStudentCommand(StudentManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean execute() {
        if (manager.getStudentCount() == 0) {
            ConsoleUI.printWarning("등록된 학생이 없습니다.");
            return true;
        }
        
        ConsoleUI.printMenuTitle("👥 학생 목록 (" + manager.getStudentCount() + "명)");
        
        System.out.println();
        ConsoleUI.printTableHeader("번호", "이름", "나이", "점수");
        
        int index = 1;
        for (Student student : manager.getAllStudents()) {
            ConsoleUI.printTableRow(
                    index++,
                    student.getName(),
                    student.getAge(),
                    String.format("%.2f", student.getScore())
            );
        }
        
        ConsoleUI.printTableFooter();
        ConsoleUI.pressEnterToContinue();
        
        return true;
    }
}
