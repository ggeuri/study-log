package command;

import java.util.List;
import java.util.Scanner;
import model.Student;
import model.StudentManager;
import ui.ConsoleUI;

/**
 * SortCommand 클래스
 * 
 * 학생 정보를 다양한 기준으로 정렬하여 표시합니다.
 * 이름순, 나이순, 점수순 정렬을 지원합니다.
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class SortCommand implements menu.MenuCommand {
    
    private StudentManager manager;
    private Scanner scanner;

    public SortCommand(StudentManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        if (manager.getStudentCount() == 0) {
            ConsoleUI.printError("등록된 학생이 없습니다.");
            return false;
        }
        
        ConsoleUI.printMenuTitle("🔄 학생 정렬");
        System.out.println();
        ConsoleUI.printMenuItem(1, "이름순 정렬");
        ConsoleUI.printMenuItem(2, "나이순 정렬");
        ConsoleUI.printMenuItem(3, "점수순 정렬 (높은순)");
        ConsoleUI.printMenuItem(4, "점수순 정렬 (낮은순)");
        ConsoleUI.printBackOption(5);
        System.out.print("\n선택: ");
        
        String choice = scanner.nextLine().trim();
        
        List<Student> students = manager.getAllStudents();
        
        switch (choice) {
            case "1":
                students.sort((a, b) -> a.getName().compareTo(b.getName()));
                ConsoleUI.printSuccess("이름순으로 정렬되었습니다.");
                break;
            case "2":
                students.sort((a, b) -> Integer.compare(a.getAge(), b.getAge()));
                ConsoleUI.printSuccess("나이순으로 정렬되었습니다.");
                break;
            case "3":
                students.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
                ConsoleUI.printSuccess("점수순 (높은순)으로 정렬되었습니다.");
                break;
            case "4":
                students.sort((a, b) -> Double.compare(a.getScore(), b.getScore()));
                ConsoleUI.printSuccess("점수순 (낮은순)으로 정렬되었습니다.");
                break;
            case "5":
                return true;
            default:
                ConsoleUI.printError("잘못된 선택입니다.");
                return false;
        }
        
        // 정렬된 목록 표시
        displaySortedList(students);
        ConsoleUI.pressEnterToContinue();
        
        return true;
    }
    
    /**
     * 정렬된 학생 목록을 표시합니다.
     */
    private void displaySortedList(List<Student> students) {
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
