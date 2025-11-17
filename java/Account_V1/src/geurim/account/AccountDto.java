package geurim.account;

public class AccountDto {
    private String date; 
    private String inoutType; 
    private String category; 
    private int amount; 
    private String memo; 

    public AccountDto(){}

    public AccountDto(String date, String inoutType,String category,int amount,String memo){
        this.date = date;
        this.inoutType = inoutType;
        this.category = category;
        this.amount = amount;
        this.memo = memo;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }
    public void setInoutType(String inoutType){
        this.inoutType = inoutType;
    }

    public String getInoutType(){
        return inoutType;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }
    public void setMemo(String memo){
        this.memo = memo;
    }

    public String getMemo(){
        return memo;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

}


// * 데이터 구조
// (데이터명(타입): 예시)
// 날짜(문자): 2025-10-22
// 유형(문자): 수입, 지출
// 카테고리(문자): 식비, 공과금, 교통비, 월급.. 등등
// 금액(숫자): 12000
// 메모(문자): 점심