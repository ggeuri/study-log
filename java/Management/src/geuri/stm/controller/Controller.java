package geuri.stm.controller;

import geuri.stm.service.Service;
import geuri.stm.util.IoManager;

public class Controller {
    Service service = new Service();
    
    public void run() {
        welcome();

        while(true){
            showMenu();
            String selectedMenu = inputCommand();
            if(selectedMenu.equals("0")){break;}
            process(selectedMenu);
        }
        
        bye();


    }



    private void welcome(){
        IoManager.println("학생관리프로그램");
    }

    // private void bye(){
    //     IoManager.println("프로그램을 종료합니다");
    // }
    
    private void showMenu(){
            IoManager.println("1. 학생 정보 등록");
            IoManager.println("2. 학생 정보 목록");
            IoManager.println("3. 학생 정보 검색");
            IoManager.println("4. 학생 정보 삭제");
            IoManager.println("5. 학생 정보 수정");
            IoManager.println("6. 학생 정보 통계");
            IoManager.println("0. 프로그램 종료");      
    }

    private String inputCommand(){
        IoManager.print("메뉴를 입력하세요 > ");  
        String command = IoManager.inputCommand();
        return command;
    }
    
    private void process(String command){
        if(command.equals("1")){
            service.addStudent();
        }
        else if(command.equals("2")){
            service.listStudent();
        }
        else if(command.equals("3")){
            service.searchStudent();
        }
        else if(command.equals("4")){
            service.deleteStudent();
        }
        else if(command.equals("5")){
            service.updateStudent();
        }
        else if(command.equals("6")){
            service.calculateStudent();
        } else {
            IoManager.println("잘못된 입력입니다.");
            IoManager.println("다시 입력해주세요.");
        }
        IoManager.pause();

    }

    private void bye(){
        IoManager.println("프로그램을 종료합니다.");
    }




}
