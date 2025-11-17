package controller;

import java.util.Scanner;

import service.Service;

public class Controller {
    Service service = new Service();
    Scanner scanner = new Scanner(System.in);
    

    public void run(){
        welcome();
        while(true){
        showMenu();
        String command = inputCommand();
        if(command.equals("0")) break;
        serviceProcess(command);
        pause();
        }
        bye();


    }





    public void welcome(){
        System.out.println("=============");
        System.out.println("환영합니다.");
        System.out.println("학생관리프로그램입니다.");
        System.out.println("=============");
    }

    public void showMenu(){
        System.out.println("1. 학생 정보 등록");
        System.out.println("2. 학생 정보 출력"); // listStudent
        System.out.println("3. 학생 정보 검색"); // searchStudent
        System.out.println("4. 학생 정보 수정"); // updateStudent
        System.out.println("5. 학생 정보 삭제"); // deleteStudent
        System.out.println("6. 학생 정보 통계"); // calculateScore
        System.out.println("0. 프로그램 종료");
    }

    public String inputCommand(){
        System.out.print("원하는 메뉴의 번호를 입력하세요 > ");
        String value = scanner.nextLine();
        
        while (!value.matches("[0-6]")) {
                System.out.println("올바른 숫자를 입력해주세요.");
                System.out.print("원하는 메뉴의 번호를 입력하세요 > ");
                value = scanner.nextLine();
            } 
            
            return value; 
         
    }

    public void serviceProcess(String command){
        switch (command) {
            case "1": service.addStudent(); break;
            case "2": service.listStudent(); break;
            case "3": service.searchStudent(); break;
            case "4": service.updateStudent(); break;
            case "5": service.deleteStudent(); break;
            case "6": service.calculateScore(); break;
        
            default: System.out.println("올바른 메뉴를 선택해주세요");
        }
        

    }

     public void pause(){
        System.out.print("계속하시려면 enter를 입력해주세요. > ");
        scanner.nextLine();
     }

     public void bye(){
        System.out.println("=============");
        System.out.println("학생관리프로그램을 종료합니다.");
        System.out.println("=============");
    }


    }