package command;

import java.util.List;
import java.util.Scanner;
import model.Student;
import model.StudentManager;
import model.StudentStats;
import ui.ConsoleUI;

/**
 * SearchByScoreCommand 클래스
 * 
 * 특정 점수 범위의 학생들을 검색합니다.
 * 우수 학생(80점 이상) 조회 등의 기능을 제공합니다.
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class SearchByScoreCommand implements menu.MenuCommand {
    
    private StudentManager manager;
    private Scanner scanner;

    public SearchByScoreCommand(StudentManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        if (manager.getStudentCount() == 0) {
            ConsoleUI.printError("등록된 학생이 없습니다.");
            return false;
        }
        
        ConsoleUI.printMenuTitle("🔍 점수 범위 검색");
        System.out.println();
        ConsoleUI.printMenuItem(1, "우수 학생 조회 (80점 이상)");
        ConsoleUI.printMenuItem(2, "사용자 정의 범위 검색");
        ConsoleUI.printBackOption(3);
        System.out.print("\n선택: ");
        
        String choice = scanner.nextLine().trim();
        
        switch (choice) {
            case "1":
                searchTopStudents();
                break;
            case "2":
                searchCustomRange();
                break;
            case "3":
                return true;
            default:
                ConsoleUI.printError("잘못된 선택입니다.");
                return false;
        }
        
        ConsoleUI.pressEnterToContinue();
        return true;
    }
    
    /**
     * 우수 학생을 조회합니다.
     */
    private void searchTopStudents() {
        StudentStats stats = new StudentStats(manager.getAllStudents());
        List<Student> topStudents = stats.getTopStudents();
        
        if (topStudents.isEmpty()) {
            ConsoleUI.printWarning("80점 이상의 학생이 없습니다.");
            return;
        }
        
        ConsoleUI.printMenuTitle("⭐ 우수 학생 (" + topStudents.size() + "명)");
        displayStudentList(topStudents);
    }
    
    /**
     * 사용자가 지정한 범위로 검색합니다.
     */
    private void searchCustomRange() {
        System.out.print("\n최소 점수 입력: ");
        String minStr = scanner.nextLine().trim();
        
        System.out.print("최대 점수 입력: ");
        String maxStr = scanner.nextLine().trim();
        
        try {
            double minScore = Double.parseDouble(minStr);
            double maxScore = Double.parseDouble(maxStr);
            
            if (minScore > maxScore) {
                ConsoleUI.printError("최소 점수가 최대 점수보다 클 수 없습니다.");
                return;
            }
            
            if (minScore < 0 || maxScore > 100) {
                ConsoleUI.printError("점수는 0~100 범위여야 합니다.");
                return;
            }
            
            StudentStats stats = new StudentStats(manager.getAllStudents());
            List<Student> result = stats.getStudentsByScoreRange(minScore, maxScore);
            
            if (result.isEmpty()) {
                ConsoleUI.printWarning(
                        String.format("%.1f~%.1f점 범위의 학생이 없습니다.", minScore, maxScore)
                );
                return;
            }
            
            ConsoleUI.printMenuTitle(
                    String.format("🔎 검색 결과 (%.1f~%.1f점, %d명)", minScore, maxScore, result.size())
            );
            displayStudentList(result);
            
        } catch (NumberFormatException e) {
            ConsoleUI.printError("올바른 숫자를 입력해주세요.");
        }
    }
    
    /**
     * 학생 목록을 표시합니다.
     */
    private void displayStudentList(List<Student> students) {
        System.out.println();
        ConsoleUI.printTableHeader("번호", "이름", "나이", "점수");
        
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            ConsoleUI.printTableRow(
                    i + 1,
                    s.getName(),
                    s.getAge(),
                    String.format("%.2f", s.getScore())
            );
        }
        
        ConsoleUI.printTableFooter();
    }
}
