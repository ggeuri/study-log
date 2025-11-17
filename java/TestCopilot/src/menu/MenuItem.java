package menu;

/**
 * MenuItem 클래스
 * 
 * 컴포지트 패턴의 Leaf 역할을 수행하는 클래스입니다.
 * 실제 명령을 실행하는 메뉴 항목입니다.
 * 
 * 책임:
 * - 메뉴 항목의 이름 관리
 * - MenuCommand를 실행
 * - 자식 메뉴 없음 (리프 노드)
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class MenuItem implements Menu {
    
    // ==================== 멤버 변수 ====================
    
    /** 메뉴 항목의 이름 */
    private String name;
    
    /** 이 메뉴 항목이 실행할 커맨드 */
    private MenuCommand command;

    
    // ==================== 생성자 ====================
    
    /**
     * MenuItem 생성자
     * 
     * @param name 메뉴 항목의 이름
     * @param command 실행할 명령
     */
    public MenuItem(String name, MenuCommand command) {
        this.name = name;
        this.command = command;
    }

    
    // ==================== Menu 인터페이스 구현 ====================
    
    /**
     * 이 메뉴 항목을 실행합니다.
     * 
     * 할당된 MenuCommand를 실행합니다.
     * 
     * @return 명령 실행 성공 여부
     */
    @Override
    public boolean execute() {
        // MenuCommand를 실행하고 그 결과 반환
        return command.execute();
    }

    /**
     * MenuItem은 자식이 없는 리프 노드입니다.
     * 
     * @return 항상 true
     */
    @Override
    public boolean isLeaf() {
        return true;
    }

    /**
     * 메뉴 항목의 이름을 반환합니다.
     * 
     * @return 메뉴 항목의 이름
     */
    @Override
    public String getName() {
        return name;
    }
}
