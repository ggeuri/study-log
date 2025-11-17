package geurim.account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private List<AccountDto> accountList = new ArrayList<>();
    

    public void save(AccountDto accountDto){
        accountList.add(accountDto);
    }

    public List<AccountDto> totalTransfer(){
       
        return accountList;
    }

    public List<AccountDto> deleteTransfer(int deleteIndex){
        accountList.remove(deleteIndex-1);

        return accountList; 
    }





}
        
        
    





// * 데이터 구조
// (데이터명(타입): 예시)
// 날짜(문자): 2025-10-22
// 유형(문자): 수입, 지출
// 카테고리(문자): 식비, 공과금, 교통비, 월급.. 등등
// 금액(숫자): 12000
// 메모(문자): 점심