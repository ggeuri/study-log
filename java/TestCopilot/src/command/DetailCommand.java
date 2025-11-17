package command;

import java.util.Scanner;
import model.Student;
import model.StudentManager;
import ui.ConsoleUI;

/**
 * DetailCommand 클래스
 * 
 * 특정 학생의 상세 정보와 성적 평가를 표시합니다.
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class DetailCommand implements menu.MenuCommand {
    
    private StudentManager manager;
    private Scanner scanner;

    public DetailCommand(StudentManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        if (manager.getStudentCount() == 0) {
            ConsoleUI.printError("등록된 학생이 없습니다.");
            return false;
        }
        
        System.out.print("\n조회할 학생의 이름: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            ConsoleUI.printError("이름은 비워둘 수 없습니다.");
            return false;
        }
        
        // 학생 검색
        Student found = null;
        for (Student student : manager.getAllStudents()) {
            if (student.getName().equals(name)) {
                found = student;
                break;
            }
        }
        
        if (found == null) {
            ConsoleUI.printError("해당하는 학생을 찾을 수 없습니다.");
            return false;
        }
        
        // 상세 정보 표시
        displayDetailInfo(found);
        ConsoleUI.pressEnterToContinue();
        
        return true;
    }
    
    /**
     * 학생의 상세 정보를 표시합니다.
     */
    private void displayDetailInfo(Student student) {
        ConsoleUI.printMenuTitle("📋 학생 상세 정보");
        
        System.out.println();
        System.out.println(ConsoleUI.CYAN + "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.YELLOW + "  📌 기본 정보" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.CYAN + "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ConsoleUI.RESET);
        System.out.println("    이름: " + ConsoleUI.BOLD + student.getName() + ConsoleUI.RESET);
        System.out.println("    나이: " + student.getAge() + "세");
        System.out.println();
        
        System.out.println(ConsoleUI.CYAN + "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.YELLOW + "  📊 성적 정보" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.CYAN + "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ConsoleUI.RESET);
        
        double score = student.getScore();
        String scoreColor = getScoreColor(score);
        String grade = getGrade(score);
        String gradeComment = getGradeComment(score);
        
        System.out.println("    점수: " + scoreColor + String.format("%.2f점", score) + ConsoleUI.RESET);
        System.out.println("    학점: " + scoreColor + grade + ConsoleUI.RESET);
        System.out.println("    평가: " + gradeComment);
        System.out.println();
        
        System.out.println(ConsoleUI.CYAN + "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ConsoleUI.RESET);
        System.out.println();
    }
    
    /**
     * 점수에 해당하는 색상을 반환합니다.
     */
    private String getScoreColor(double score) {
        if (score >= 90) return ConsoleUI.GREEN;
        if (score >= 80) return ConsoleUI.CYAN;
        if (score >= 70) return ConsoleUI.YELLOW;
        if (score >= 60) return ConsoleUI.MAGENTA;
        return ConsoleUI.RED;
    }
    
    /**
     * 점수에 해당하는 학점을 반환합니다.
     */
    private String getGrade(double score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
    
    /**
     * 점수에 해당하는 평가 의견을 반환합니다.
     */
    private String getGradeComment(double score) {
        if (score >= 95) return ConsoleUI.GREEN + "🌟 탁월한 성적입니다!" + ConsoleUI.RESET;
        if (score >= 90) return ConsoleUI.GREEN + "👏 매우 우수합니다!" + ConsoleUI.RESET;
        if (score >= 80) return ConsoleUI.CYAN + "✓ 좋은 성적입니다." + ConsoleUI.RESET;
        if (score >= 70) return ConsoleUI.YELLOW + "○ 평균 수준입니다." + ConsoleUI.RESET;
        if (score >= 60) return ConsoleUI.MAGENTA + "△ 개선이 필요합니다." + ConsoleUI.RESET;
        return ConsoleUI.RED + "✗ 노력이 필요합니다." + ConsoleUI.RESET;
    }
}
