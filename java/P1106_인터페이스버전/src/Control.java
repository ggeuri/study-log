

public class Control {
    private final MemoryRepo memoryRepo = new MemoryRepo();
    private final Service service = new Service(memoryRepo); 

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
        try {
            Command processNum = Command.from(command);
            processNum.execute(service);
        } catch (IllegalArgumentException e) {
            IoManager.print("잘못된 선택입니다.");
            // TODO: handle exception
        }
        
    }

    public enum Command{
        ADDSTUDENT("1") {
            @Override
            public void execute(Service service){
                service.addStudent();
            }
        }
        
        ,LISTSTUDENT("2"){
            @Override
            public void execute(Service service){
                service.listStudent();
            }
        }
        ,SEARCHSTUDENT("3"){
            @Override
            public void execute(Service service){
                service.searchStudent();
            }
        }
        ,DELETEDSTUDENT("4"){
            @Override
            public void execute(Service service){
                service.deletedStudent();
            }
        }
        ,UPDATESTUDENT("5"){
            @Override
            public void execute(Service service){
                service.updateStudent();
            }
        }
        ,CALCULATESCORE("6"){
            @Override
            public void execute(Service service){
                service.calculateScore();
            }
        };

        private final String code; 
        Command(String code){this.code = code;}
        public String getCode(){return code;}

        public abstract void execute(Service service);

        public static Command from(String input) {
            for (Command e : values()) {
                if(e.code.equals(input))
                 return e;                
            }
            throw new IllegalArgumentException();
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

    
  