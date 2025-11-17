package command;

import model.StudentManager;
import persistence.StudentData;

/**
 * SaveStudentCommand 클래스
 * 
 * 학생 정보를 파일에 저장하는 커맨드 클래스입니다.
 * 
 * SRP 원칙: 파일 저장 기능만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class SaveStudentCommand implements menu.MenuCommand {
    
    private StudentManager manager;

    public SaveStudentCommand(StudentManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean execute() {
        System.out.println();
        StudentData.saveStudents(manager);
        System.out.println();
        return true;
    }
}
