package com.joongang.stm.controller;

import com.joongang.stm.service.Service;
import com.joongang.stm.util.IoManager;

//컴포넌트
//현재 전체 흐름을 담당한다.
// 나중에 Spring에서는 사용자 입출력 담당임(최전방) 
public class Controller {
    private Service service = new Service();

    public void run(){//결과적으로 App이 쓰게될 코드를 여기다가 짜는거임 -> 쓰고연결해줘야함
        welcome();
        while(true){
            showMenu();
            String command = selectMenu();
            if (isExitCommand(command)){
                break;
            }
            processCommand(command);
            pause();      
        }    
        bye();
    }
    
    private void welcome(){
        IoManager.print("**********************");
        IoManager.print("  학생 관리 프로그램      ");
        IoManager.print("      version 2      ");
        IoManager.print("      20251017      ");
        IoManager.print("**********************");
        
    }
    
    private void showMenu(){
        IoManager.print("      [ 메뉴 ]"); // static이니까 new안해도된다! 
        IoManager.print("1. 학생 정보 등록");
        IoManager.print("2. 학생 정보 목록");
        IoManager.print("3. 학생 정보 검색");
        IoManager.print("4. 학생 정보 삭제");
        IoManager.print("5. 학생 정보 수정");
        IoManager.print("6. 학생 정보 통계");
        IoManager.print("0. 프로그램 종료");
    }
    
    private void bye(){
        IoManager.print("프로그램이 종료됩니다.");
        IoManager.print("이용해주셔서 감사합니다.");
    }
    
    private String selectMenu(){
        String command = IoManager.input("선택 > ");
        return command;
    }
    
    private boolean isExitCommand(String command){
        return command.equals("0"); // 종료 
    }
    
    private void processCommand(String command){  // 핵심코어로직 = 비즈니스 로직 = 클래스 따로 뜯어서 작성 Three-Layer Architecture = Service에 작성 
        // Service service = new Service(); //종속. 컴포지션 관계 

        if (command.equals("1")){
            service.addStudent();
        } else if(command.equals("2")){
            service.listStudent();
        } else if(command.equals("3")){
            service.searchStudent();
        } else if(command.equals("4")){
            service.deleteStudent();
        } else if(command.equals("5")){
        
        } else if(command.equals("6")){
            service.statistize();
        } else {
            IoManager.print("잘못된 명령을 입력하셨습니다.");
            IoManager.print("다시 입력해주세요.");
        }
    }
    private void pause(){
        IoManager.pause();
    }
}