package com.joongang.stm.controller;

import com.joongang.stm.service.Service;
import com.joongang.stm.util.IoManager;

//컴포넌트
//현재 전체 흐름을 담당한다.
// 나중에 Spring에서는 사용자 입출력 담당임(최전방) 
public class Controller {
    private Service service = new Service();

    public void run() {
        service.load();
        IoManager.print("학생정보 프로그램 V3");
        
        while (true) {
            
            IoManager.print("=======[메뉴]=======");
            IoManager.print("1. 학생 정보 등록");
            IoManager.print("2. 학생 정보 출력");
            IoManager.print("3. 학생 정보 검색");
            IoManager.print("4. 학생 정보 삭제");
            IoManager.print("0. 종료");

            String command = IoManager.input("메뉴 선택 > ");
            if(command.equals("0")) break;

            switch (command) {
                case "1": service.addStudent(); break;
                case "2": service.listStudent();break;
                case "3": service.searchStudent();break;
                case "4": service.removeStudent();break;
                default : IoManager.print("다시 입력하세요."); 
            }
            
            IoManager.pause();
        }
        service.save();
        IoManager.print("프로그램을 종료합니다.");
    }

}