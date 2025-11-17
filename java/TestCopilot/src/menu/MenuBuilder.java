package menu;

import java.util.Scanner;
import model.StudentManager;
import command.*;

/**
 * MenuBuilder 클래스
 * 
 * 메뉴 시스템을 구성하는 책임을 가지는 빌더 클래스입니다.
 * 컴포지트 패턴의 트리 구조를 조립합니다.
 * 
 * SRP 원칙: 메뉴 구조 생성만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class MenuBuilder {
    
    /**
     * 전체 메뉴 구조를 생성하여 루트 메뉴를 반환합니다.
     * 
     * 메뉴 구조:
     * - 메인 메뉴
     *   - 학생 관리
     *     - 학생 등록
     *     - 학생 삭제
     *     - 학생 수정
     *     - 학생 목록
     *     - 학생 검색
     *     - 학생 상세 정보
     *   - 통계 및 분석
     *     - 성적 통계
     *     - 학생 정렬
     *     - 점수 범위 검색
     *   - 파일 관리
     *     - 파일에 저장
     *     - 파일에서 로드
     * 
     * @param manager StudentManager 인스턴스
     * @param scanner Scanner 인스턴스
     * @return 루트 메뉴 (MenuGroup)
     */
    public static MenuGroup buildMainMenu(StudentManager manager, Scanner scanner) {
        // 루트 메뉴 생성
        MenuGroup rootMenu = new MenuGroup("학생 관리 프로그램", scanner);
        
        // ===== 학생 관리 메뉴 =====
        MenuGroup studentManageMenu = new MenuGroup("학생 관리", scanner);
        studentManageMenu.addMenu(new MenuItem("학생 등록", new AddStudentCommand(manager, scanner)));
        studentManageMenu.addMenu(new MenuItem("학생 삭제", new DeleteStudentCommand(manager, scanner)));
        studentManageMenu.addMenu(new MenuItem("학생 수정", new UpdateStudentCommand(manager, scanner)));
        studentManageMenu.addMenu(new MenuItem("학생 목록", new ListStudentCommand(manager)));
        studentManageMenu.addMenu(new MenuItem("학생 검색", new SearchStudentCommand(manager, scanner)));
        studentManageMenu.addMenu(new MenuItem("학생 상세 정보", new DetailCommand(manager, scanner)));
        
        // ===== 통계 및 분석 메뉴 =====
        MenuGroup analysisMenu = new MenuGroup("통계 및 분석", scanner);
        analysisMenu.addMenu(new MenuItem("성적 통계", new StatsCommand(manager)));
        analysisMenu.addMenu(new MenuItem("학생 정렬", new SortCommand(manager, scanner)));
        analysisMenu.addMenu(new MenuItem("점수 범위 검색", new SearchByScoreCommand(manager, scanner)));
        
        // ===== 파일 관리 메뉴 =====
        MenuGroup fileManageMenu = new MenuGroup("파일 관리", scanner);
        fileManageMenu.addMenu(new MenuItem("파일에 저장", new SaveStudentCommand(manager)));
        fileManageMenu.addMenu(new MenuItem("파일에서 로드", new LoadStudentCommand(manager, scanner)));
        
        // 루트 메뉴에 서브 메뉴 추가
        rootMenu.addMenu(studentManageMenu);
        rootMenu.addMenu(analysisMenu);
        rootMenu.addMenu(fileManageMenu);
        
        return rootMenu;
    }
}
