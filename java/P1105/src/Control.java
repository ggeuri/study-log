import java.util.Scanner;

public class Control {
    Service service = new Service();
    Scanner scanner = new Scanner(System.in);
    public void run(){
        welcome();
        while (true) {
            showMenu();
            input("입력 값 > ");
            String command = scanner.nextLine().trim();
            if(command.equals("0")) break;
            runProcess(command);
        }
        bye();

    }


    public void welcome(){
        System.out.println("=========================");
        System.out.println("    학생 관리 프로그램    ");
        System.out.println("=========================");
    }

    public void showMenu(){
            System.out.println("*** 메뉴 ***");
            System.out.println("1. 학생 정보 등록");
            System.out.println("2. 학생 정보 목록");
            System.out.println("3. 학생 정보 검색");
            System.out.println("4. 학생 정보 삭제");
            System.out.println("5. 학생 정보 수정"); 
            System.out.println("6. 학생 정보 통계"); 
            System.out.println("0. 프로그램 종료");  
    }

    public void input(String text){
        System.out.print(text);
    }

    public void runProcess(String command){
        switch (command) {
            case "1": service.addStudent(); break;
            case "2": service.listStudent(); break;
            case "3": service.searchStudent(); break;
            case "4": service.deletedStudent(); break;
            case "5": service.updateStudent(); break;
            case "6": service.calculateScore(); break;
            default: System.out.println("잘못된 선택입니다."); break;
        }
                
        }
    public void bye(){
        System.out.println("=========================");
        System.out.println("    Bye~    ");
        System.out.println("=========================");
    }

    }

    
