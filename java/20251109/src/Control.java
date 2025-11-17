public class Control {
    
    private final Service service;

    public Control(Repo repo) {
        this.service = new Service(repo);
    }

    public void run(){
        welcome();
        while (true) {
            showMenu();
            String command = requestInput();
            if(command.equals(exitNum())) break;
            runProcess(command);
            pause();
        }
        bye();

    }

    public void welcome(){
        IoManager.print("=====================");
        IoManager.print("[학생관리프로그램]");
        IoManager.print("=====================");
    }
    
    public void showMenu(){
        IoManager.print("[메뉴]");
        IoManager.print("1. 학생 등록");
        IoManager.print("2. 학생 목록");
        IoManager.print("3. 학생 검색");
        IoManager.print("4. 학생 삭제");
        IoManager.print("5. 학생 수정");
        IoManager.print("6. 학생 통계");
        IoManager.print("0. 종료");
    }
    
    public String requestInput(){
        return IoManager.input("입력 값 > ");
    }
    
    public String exitNum(){
        return "0";
    }

    public void runProcess(String command ){
        try {
            Command processCommand = Command.fromCode(command); 
            processCommand.execuate(service);
            
        } catch (Exception e) {
            e.printStackTrace();
            IoManager.print("잘못된 번호를 선택하셨습니다.");
        }
        

    }

    public void pause(){
        IoManager.pause();
    }

    public void bye(){
        IoManager.print("=====================");
        IoManager.print("[프로그램 종료]");
        IoManager.print("=====================");
    }

    public enum Command{
        ADDSTUDENT("1"){
            public void execuate(Service service){
                service.addStudent();
            }
        }
        ,LISTSTUDENT("2"){
            public void execuate(Service service){
                service.listStudent();
            }
        }
        ,SEARCHSTUDENT("3"){
            public void execuate(Service service){
                service.searchStudent();
            }
        }
        ,DELETESTUDENT("4"){
            public void execuate(Service service){
                service.deleteStudent();
            }
        }
        ,UPDATESTUDENT("5"){
            public void execuate(Service service){
                service.updateStudent();
            }
        }
        ,CALCULATESCORE("6"){
            public void execuate(Service service){
                service.calculateScore();
            }
        }; 

        private final String code ; 
        Command(String code){this.code = code;}
        public String getCode(){return code;}

        public static Command fromCode(String command){
            for (Command e : Command.values()) {
                if(e.code.equalsIgnoreCase(command))
                return e; 
            }
            throw new IllegalArgumentException();
        }

        public abstract void execuate(Service service);
    }

}
