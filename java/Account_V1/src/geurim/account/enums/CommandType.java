package geurim.account.enums;

public enum CommandType {
    ADD(1, "거래 등록"),
    TOTALLIST(2, "거래 목록"),
    DELETE(3, "거래 삭제"),
    SUM(4, "카테고리 합계"),
    DESC(5, "금액 내림차순 목록"),
    SEARCH(6, "거래 검색"),
    EXIT(7, "나가기");
    
    private final int code; // 이게 사용자가 입력할 1~7
    private final String label; // 내가 볼 "거래 등록" 등 

    CommandType(int code, String label) { // 입력받을때 라벨도 받아야..하나?
        this.code = code;
        this.label = label;
    }    
    
    
    // service.addTransfer();
    //     } else if(selectCommand.equals("2")){
    //         service.totalTransfer();
    //     } else if(selectCommand.equals("3")){
    //         service.deleteTransfer();
    //     } else if(selectCommand.equals("4")){
    //         service.sumByCategory();
    //     } else if(selectCommand.equals("5")){
    //         service.descAmount();
    //     } else if(selectCommand.equals("6")){
    //         service.searchMemo();

    

}
