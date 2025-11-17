package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ui.ConsoleUI;

/**
 * MenuGroup 클래스
 * 
 * 컴포지트 패턴의 Composite 역할을 수행하는 클래스입니다.
 * 다른 메뉴들(MenuItem 또는 MenuGroup)을 포함할 수 있는 그룹입니다.
 * 
 * 책임:
 * - 자식 메뉴들을 관리 (추가)
 * - 자식 메뉴들을 표시
 * - 사용자 선택에 따라 자식 메뉴 실행
 * - 재귀적 메뉴 구조 지원
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class MenuGroup implements Menu {
    
    // ==================== 멤버 변수 ====================
    
    /** 메뉴 그룹의 이름 */
    private String name;
    
    /** 이 그룹에 속한 자식 메뉴들 */
    private List<Menu> children;
    
    /** 사용자 입력을 받기 위한 Scanner */
    private Scanner scanner;

    
    // ==================== 생성자 ====================
    
    /**
     * MenuGroup 생성자
     * 
     * @param name 메뉴 그룹의 이름
     * @param scanner 사용자 입력을 위한 Scanner 객체
     */
    public MenuGroup(String name, Scanner scanner) {
        this.name = name;
        this.children = new ArrayList<>();
        this.scanner = scanner;
    }

    
    // ==================== 자식 메뉴 관리 메서드 ====================
    
    /**
     * 자식 메뉴를 추가합니다.
     * 
     * MenuItem 또는 다른 MenuGroup을 추가할 수 있습니다.
     * 이를 통해 재귀적인 트리 구조를 구성합니다.
     * 
     * @param menu 추가할 메뉴 (MenuItem 또는 MenuGroup)
     */
    public void addMenu(Menu menu) {
        // children 리스트에 메뉴 추가
        children.add(menu);
    }

    
    // ==================== Menu 인터페이스 구현 ====================
    
    /**
     * 이 메뉴 그룹을 실행합니다.
     * 
     * 자식 메뉴들을 표시하고 사용자 선택을 받아 해당 메뉴를 실행합니다.
     * 
     * @return 메뉴 처리 성공 여부
     */
    @Override
    public boolean execute() {
        // 무한 루프로 메뉴 처리
        while (true) {
            // 자식 메뉴들을 화면에 표시
            displayMenus();
            
            // 사용자의 선택 입력 받기
            System.out.print("선택: ");
            String choice = scanner.nextLine().trim();
            
            // 입력을 정수로 변환 시도
            try {
                int selected = Integer.parseInt(choice);
                
                // 뒤로가기 옵션 (번호가 children 개수 + 1)
                if (selected == children.size() + 1) {
                    return true; // 메뉴 그룹 종료, 부모 메뉴로 돌아감
                }
                
                // 입력된 번호가 유효한 범위인지 확인 (1 ~ children.size())
                if (selected >= 1 && selected <= children.size()) {
                    // 선택된 메뉴를 실행
                    Menu selectedMenu = children.get(selected - 1);
                    selectedMenu.execute();
                } else {
                    // 범위 밖의 입력
                    ConsoleUI.printError("잘못된 선택입니다.");
                }
                
            } catch (NumberFormatException e) {
                // 숫자가 아닌 입력
                ConsoleUI.printError("숫자를 입력해주세요.");
            }
        }
    }

    /**
     * MenuGroup은 자식이 있는 복합 노드입니다.
     * 
     * @return 항상 false
     */
    @Override
    public boolean isLeaf() {
        return false;
    }

    /**
     * 메뉴 그룹의 이름을 반환합니다.
     * 
     * @return 메뉴 그룹의 이름
     */
    @Override
    public String getName() {
        return name;
    }

    
    // ==================== 메뉴 표시 메서드 ====================
    
    /**
     * 이 그룹의 모든 자식 메뉴를 화면에 표시합니다.
     * 
     * 각 자식 메뉴의 이름과 번호를 함께 출력합니다.
     * 마지막에 "뒤로가기" 옵션을 추가합니다.
     */
    private void displayMenus() {
        ConsoleUI.printMenuTitle(name);
        System.out.println();
        
        // 모든 자식 메뉴를 순회하여 표시
        for (int i = 0; i < children.size(); i++) {
            // 번호(1부터 시작)와 메뉴 이름 출력
            ConsoleUI.printMenuItem(i + 1, children.get(i).getName());
        }
        
        // 뒤로가기 옵션 표시
        ConsoleUI.printBackOption(children.size() + 1);
    }
}
