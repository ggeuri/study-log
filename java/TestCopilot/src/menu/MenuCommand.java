package menu;

/**
 * MenuCommand 인터페이스
 * 
 * 커맨드 패턴을 적용하여 메뉴 항목의 실행 로직을 캡슐화합니다.
 * 각 메뉴가 실행할 구체적인 동작을 정의하는 인터페이스입니다.
 * 
 * SRP 원칙:
 * - 메뉴 구조(Menu/MenuItem/MenuGroup)와 메뉴 동작(MenuCommand)을 분리
 * - 각 명령은 하나의 책임만 가짐
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public interface MenuCommand {
    
    /**
     * 메뉴 명령을 실행합니다.
     * 
     * 이 메서드는 각 구현 클래스에서 구체적인 동작을 정의합니다.
     * 예: 학생 등록, 학생 삭제, 파일 저장 등
     * 
     * @return 명령 실행 성공 여부
     */
    boolean execute();
}
