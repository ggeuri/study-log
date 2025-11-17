package command;

import model.StudentManager;
import model.StudentStats;
import ui.ConsoleUI;

/**
 * StatsCommand 클래스
 * 
 * 학생 성적 통계를 조회하는 커맨드입니다.
 * 평균, 최고점, 최저점, 점수 분포 등을 표시합니다.
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class StatsCommand implements menu.MenuCommand {
    
    private StudentManager manager;

    public StatsCommand(StudentManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean execute() {
        if (manager.getStudentCount() == 0) {
            ConsoleUI.printError("등록된 학생이 없습니다.");
            return false;
        }
        
        StudentStats stats = new StudentStats(manager.getAllStudents());
        
        ConsoleUI.printMenuTitle("📈 성적 통계");
        
        // 통계 정보 출력
        System.out.println();
        System.out.println(ConsoleUI.CYAN + "  총 학생 수: " + ConsoleUI.RESET + manager.getStudentCount() + "명");
        System.out.println(ConsoleUI.YELLOW + "  평균 점수: " + ConsoleUI.RESET + String.format("%.2f점", stats.getAverageScore()));
        System.out.println(ConsoleUI.GREEN + "  최고 점수: " + ConsoleUI.RESET + String.format("%.2f점", stats.getMaxScore()));
        System.out.println(ConsoleUI.RED + "  최저 점수: " + ConsoleUI.RESET + String.format("%.2f점", stats.getMinScore()));
        System.out.println(ConsoleUI.MAGENTA + "  평균 나이: " + ConsoleUI.RESET + String.format("%.1f세", stats.getAverageAge()));
        
        // 점수 분포 그래프
        int[] distribution = stats.getScoreDistribution();
        ConsoleUI.printScoreDistributionGraph(distribution);
        
        ConsoleUI.pressEnterToContinue();
        return true;
    }
}
