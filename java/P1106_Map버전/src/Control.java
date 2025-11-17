

public class Control {
    Service service = new Service();
    public void run(){
        welcome();
        while (true) {
            showMenu();
            String command = IoManager.input("입력 값 > ");
            if(command.equals("0")) break;
            runProcess(command);
            pause();
        }
        bye();

    }


    public void welcome(){
        IoManager.print("=========================");
        IoManager.print("    학생 관리 프로그램    ");
        IoManager.print("=========================");
    }

    public void showMenu(){
        IoManager.print("*** 메뉴 ***");
        IoManager.print("1. 학생 정보 등록");
        IoManager.print("2. 학생 정보 목록");
        IoManager.print("3. 학생 정보 검색");
        IoManager.print("4. 학생 정보 삭제");
        IoManager.print("5. 학생 정보 수정"); 
        IoManager.print("6. 학생 정보 통계"); 
        IoManager.print("0. 프로그램 종료");  
    }


    public void runProcess(String command){
        switch (command) {
            case "1": service.addStudent(); break;
            case "2": service.listStudent(); break;
            case "3": service.searchStudent(); break;
            case "4": service.deletedStudent(); break;
            case "5": service.updateStudent(); break;
            case "6": service.calculateScore(); break;
            default: IoManager.print("잘못된 선택입니다."); break;
        }
        
    }
    
    public void pause(){
        IoManager.print("================================");
        IoManager.print("계속 하시려면 Enter를 입력하세요");
        IoManager.pause();
    }

    public void bye(){
        IoManager.print("=========================");
        IoManager.print("          Bye~    ");
        IoManager.print("=========================");
    }

    

    }

    
