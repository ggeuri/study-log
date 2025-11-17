package menu;

/**
 * Menu 인터페이스
 * 
 * 컴포지트 패턴의 Component 역할을 수행하는 인터페이스입니다.
 * 메뉴 아이템과 메뉴 그룹 모두가 구현해야 할 공통 메서드를 정의합니다.
 * 
 * 역할:
 * - 단일 메뉴 항목(MenuItem)과 메뉴 그룹(MenuGroup) 모두를 같은 방식으로 처리
 * - 재귀적 구조 지원
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public interface Menu {
    
    /**
     * 메뉴를 실행합니다.
     * 
     * MenuItem의 경우: 해당 명령 실행
     * MenuGroup의 경우: 하위 메뉴 표시
     * 
     * @return 메뉴 실행 성공 여부
     */
    boolean execute();
    
    /**
     * 이 메뉴가 리프 노드(자식이 없는 끝 노드)인지 확인합니다.
     * 
     * MenuItem의 경우: true (자식 없음)
     * MenuGroup의 경우: false (자식 있음)
     * 
     * @return 리프 노드면 true, 그룹이면 false
     */
    boolean isLeaf();
    
    /**
     * 메뉴의 이름을 반환합니다.
     * 
     * @return 메뉴 이름
     */
    String getName();
}
