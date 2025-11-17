package service;

import java.util.Scanner;

import repository.Repository;

public class Service {
    Repository repository = new Repository(); 
    Scanner scanner = new Scanner(System.in);

    public void addStudent(){
        System.out.println("학생 정보 등록 로직을 시작합니다.");
        System.out.print("이름을 입력하세요 > ");
        String name = scanner.nextLine();
        
        System.out.print("나이를 입력하세요 > ");
        String inputAge = scanner.nextLine();
        while (!inputAge.matches("^(100|[1-9]?[0-9])$")) {
            System.out.println("올바른 나이를 입력하세요.");
            System.out.print("나이를 입력하세요 > ");
            inputAge = scanner.nextLine();
        }
        
        System.out.print("점수를 입력하세요 > ");
        String inputScore = scanner.nextLine();
        while (!inputScore.matches("^(100|[1-9]?[0-9])$")) {
            System.out.println("올바른 점수를 입력하세요.");
            System.out.print("점수를 입력하세요 > ");
            inputScore = scanner.nextLine();
        }
        
        int age = Integer.parseInt(inputAge);
        int score = Integer.parseInt(inputScore);

        repository.addStudent(name,age,score);
        
    }

    public void listStudent(){
        System.out.println("list");

    }
    public void searchStudent(){
        System.out.println("학생 정보 검색 로직을 시작합니다.");
        System.out.print("검색할 이름을 입력하세요 > ");
        String inputName = scanner.nextLine();

        repository.searchStudent(inputName);

    }
    public void updateStudent(){

    }
    public void deleteStudent(){

    }
    public void calculateScore(){

    }

         

}



