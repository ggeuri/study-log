package geurim.account;

public class Controller {
    Service service = new Service();
    public void run(){

        welcome(); 
        while(true){
            showMenu();
            String selectCommandNum = selectCommand();
            if(selectCommandNum.equals(selectExitNum())) break;
            processCommand(selectCommandNum);
            pause();
        }

        bye();
        
    }

    private void welcome(){
        IoManager.print("[가계부]");
    }

    private void showMenu(){
        IoManager.print("1. 거래 등록 (수입/지출)");
        IoManager.print("2. 거래 목록 및 합계");
        IoManager.print("3. 거래 삭제");
        IoManager.print("4. 카테고리별 합계");
        IoManager.print("5. 금액 내림차순 보기");
        IoManager.print("6. 검색(메모 키워드)");
        IoManager.print("7. 종료");
    }

    private String selectCommand(){
        String selectCommandNum = IoManager.input("입력 값 > ");
        return selectCommandNum;
    }

    private String selectExitNum(){
        return "7";
    }

    private void processCommand(String selectCommand){
        if(selectCommand.equals("1")){
            service.addTransfer();
        } else if(selectCommand.equals("2")){
            service.totalTransfer();
        } else if(selectCommand.equals("3")){
            service.deleteTransfer();
        } else if(selectCommand.equals("4")){
            service.sumByCategory();
        } else if(selectCommand.equals("5")){
            service.descAmount();
        } else if(selectCommand.equals("6")){
            service.searchMemo();
        } else {
            IoManager.print("잘못된 입력입니다.");
        }

    }
    private void pause(){
        IoManager.pause("계속하시려면 Enter를 입력해주세요.");
    }

    private void bye(){
        IoManager.print("프로그램을 종료합니다.");
    }



}



// * 화면 예시
// [가계부]
// 1. 거래 등록 (수입/지출)_eunm
// 2. 거래 목록 및 합계_
// 3. 거래 삭제_
// 4. 카테고리별 합계
// 5. 금액 내림차순 보기
// 6. 검색(메모 키워드)
// 7. 종료
// 선택>
