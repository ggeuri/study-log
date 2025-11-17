package controller;

import service.Service;
import util.IOManager;

public class Controller {
    Service service = new Service();
    public void run(){
        welcome();
        while (true) {
            showMenu();
            String command = IOManager.input("선택 > "); 
            line();
            if(command.equals("0")) break;
            serviceProcess(command);
            pause();
        }
        bye();
    }

    public void welcome (){
        IOManager.print("======================");
        IOManager.print("[ToDo-List]");
    }

    public void showMenu (){
        IOManager.print("======================");
        IOManager.print("1. 할 일 추가");
        IOManager.print("2. 전체 목록 보기");
        IOManager.print("3. 완료 처리");
        IOManager.print("4. 미완료만 보기");
        IOManager.print("5. 완료만 보기");
        IOManager.print("6. 제목 키워드 검색");
        IOManager.print("7. 삭제");
        IOManager.print("0. 종료");
        IOManager.print("======================");
    }

    public void serviceProcess(String command){
    switch (command) {
        case "1": service.addTask(); break;
        case "2": service.allTask(); break;
        case "3": service.markAsComplete(); break;
        case "4": service.showIncompleteTasks(); break;
        case "5": service.showCompleteTasks();break;
        case "6": service.searchTitleKeyword(); break;
        case "7": service.deleteTasks(); break;
        default: IOManager.print("잘못된 선택입니다.");
       }
    }
    public void line(){
        IOManager.print("======================");
    }
    public void pause(){
        IOManager.pause();
    }
    public void bye(){
        IOManager.print("프로그램을 종료합니다.");
        IOManager.print("이용해주셔서 감사합니다.");
    }


}
