package ui;

/**
 * ConsoleUI 클래스
 * 
 * 콘솔 기반의 사용자 인터페이스를 제공합니다.
 * 예쁜 형식으로 정보를 출력하고 시각적 효과를 제공합니다.
 * 
 * SRP 원칙: UI 표현만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class ConsoleUI {
    
    // ==================== 색상 정의 ====================
    
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String BLACK_BG = "\u001B[40m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String RED_BG = "\u001B[41m";
    
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // ==================== 로고 및 제목 출력 ====================
    
    /**
     * 프로그램 시작 화면을 출력합니다.
     */
    public static void printWelcome() {
        clearScreen();
        printLine("═", 60);
        printCentered(BOLD + BLUE + "🎓 학생 관리 프로그램 🎓" + RESET, 60);
        printLine("═", 60);
        System.out.println();
    }
    
    /**
     * 프로그램 종료 화면을 출력합니다.
     */
    public static void printGoodbye() {
        System.out.println();
        printLine("═", 60);
        printCentered(GREEN + "감사합니다! 프로그램을 종료합니다." + RESET, 60);
        printLine("═", 60);
    }
    
    // ==================== 메뉴 출력 관련 ====================
    
    /**
     * 메뉴 제목을 출력합니다.
     */
    public static void printMenuTitle(String title) {
        System.out.println();
        printLine("─", 60);
        printCentered(CYAN + BOLD + title + RESET, 60);
        printLine("─", 60);
    }
    
    /**
     * 메뉴 항목을 출력합니다.
     */
    public static void printMenuItem(int number, String text) {
        System.out.println(BLUE + "  " + number + ". " + RESET + text);
    }
    
    /**
     * 뒤로가기 옵션을 출력합니다.
     */
    public static void printBackOption(int number) {
        System.out.println(MAGENTA + "  " + number + ". 뒤로가기" + RESET);
    }
    
    // ==================== 메시지 출력 ====================
    
    /**
     * 성공 메시지를 출력합니다.
     */
    public static void printSuccess(String message) {
        System.out.println(GREEN + "✓ " + message + RESET);
    }
    
    /**
     * 오류 메시지를 출력합니다.
     */
    public static void printError(String message) {
        System.out.println(RED + "✗ " + message + RESET);
    }
    
    /**
     * 경고 메시지를 출력합니다.
     */
    public static void printWarning(String message) {
        System.out.println(YELLOW + "⚠ " + message + RESET);
    }
    
    /**
     * 정보 메시지를 출력합니다.
     */
    public static void printInfo(String message) {
        System.out.println(CYAN + "ℹ " + message + RESET);
    }
    
    // ==================== 테이블 출력 ====================
    
    /**
     * 테이블 헤더를 출력합니다.
     */
    public static void printTableHeader(String... columns) {
        printLine("═", 80);
        StringBuilder header = new StringBuilder("║ ");
        for (String column : columns) {
            header.append(padRight(column, 18)).append("║ ");
        }
        System.out.println(BOLD + BLUE + header.toString() + RESET);
        printLine("═", 80);
    }
    
    /**
     * 테이블 행을 출력합니다.
     */
    public static void printTableRow(Object... values) {
        StringBuilder row = new StringBuilder("║ ");
        for (Object value : values) {
            row.append(padRight(String.valueOf(value), 18)).append("║ ");
        }
        System.out.println(row.toString());
    }
    
    /**
     * 테이블 푸터를 출력합니다.
     */
    public static void printTableFooter() {
        printLine("═", 80);
        System.out.println();
    }
    
    // ==================== 통계 표시 ====================
    
    /**
     * 통계 정보를 카드 형식으로 출력합니다.
     */
    public static void printStatCard(String label, String value) {
        System.out.println(BLUE + "┌─────────────────────┐" + RESET);
        System.out.println(BLUE + "│ " + padRight(label, 19) + "│" + RESET);
        System.out.println(YELLOW + "│ " + padRight(value, 19) + "│" + RESET);
        System.out.println(BLUE + "└─────────────────────┘" + RESET);
    }
    
    /**
     * 점수 분포를 막대 그래프로 표시합니다.
     */
    public static void printScoreDistributionGraph(int[] distribution) {
        String[] grades = {"A (90~100)", "B (80~89)", "C (70~79)", "D (60~69)", "F (0~59)"};
        int maxCount = java.util.Arrays.stream(distribution).max().orElse(1);
        
        System.out.println();
        printCentered(BOLD + "📊 점수 분포" + RESET, 60);
        printLine("─", 60);
        
        for (int i = 0; i < grades.length; i++) {
            int barLength = (distribution[i] * 30) / (maxCount > 0 ? maxCount : 1);
            String bar = "█".repeat(Math.max(0, barLength));
            String coloredBar = getGradeColor(i) + bar + RESET;
            System.out.printf("%-12s │ %s %d명\n", grades[i], coloredBar, distribution[i]);
        }
        printLine("─", 60);
        System.out.println();
    }
    
    // ==================== 유틸리티 메서드 ====================
    
    /**
     * 화면을 지웁니다.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * 문자를 반복하여 선을 그립니다.
     */
    public static void printLine(String character, int length) {
        System.out.println(character.repeat(length));
    }
    
    /**
     * 텍스트를 중앙에 정렬합니다.
     */
    public static void printCentered(String text, int width) {
        // ANSI 색상 코드 길이를 제외한 실제 텍스트 길이 계산
        int realLength = text.replaceAll("\u001B\\[[0-9;]*m", "").length();
        int padding = (width - realLength) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }
    
    /**
     * 문자열을 오른쪽 정렬합니다.
     */
    public static String padRight(String text, int length) {
        if (text.length() >= length) {
            return text.substring(0, length);
        }
        return text + " ".repeat(length - text.length());
    }
    
    /**
     * 학점에 따른 색상을 반환합니다.
     */
    private static String getGradeColor(int gradeIndex) {
        switch (gradeIndex) {
            case 0: return GREEN;      // A - Green
            case 1: return CYAN;       // B - Cyan
            case 2: return YELLOW;     // C - Yellow
            case 3: return RED;        // D - Red
            default: return RED;       // F - Red
        }
    }
    
    /**
     * 입력을 대기하고 Enter 키를 누르도록 유도합니다.
     */
    public static void pressEnterToContinue() {
        System.out.println();
        System.out.print(MAGENTA + "Enter 키를 눌러주세요..." + RESET);
        try {
            System.in.read();
        } catch (Exception e) {
            // 무시
        }
        System.out.println();
    }
}
