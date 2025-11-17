package geurim.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {
    private Repository repository = new Repository();

    public void addTransfer(){
        IoManager.print("거래 등록을 선택하셨습니다.");
        String date = IoManager.input("거래일자를 입력하세요 > ");
        String inoutType = IoManager.input("수입, 지출 여부를 입력하세요 > ");
        String category = IoManager.input("카테고리를 입력하세요 > ");
        int amount = Integer.parseInt(IoManager.input("금액을 입력하세요 > "));
        String memo = IoManager.input("메모를 입력하세요 > ");

        AccountDto accountDto = new AccountDto(date,inoutType,category,amount,memo);

        repository.save(accountDto);

        IoManager.print("거래가 등록되었습니다.");
    }

    public void totalTransfer(){
        IoManager.print("거래 목록 및 합계를 선택하셨습니다.");
        List<AccountDto> list = repository.totalTransfer();
        int sum = 0;
        int count = 1 ;
         for(AccountDto element : list) {// list의 크기만큼 반복 
            String text = "";
            text += count + ". 거래일자: "+ element.getDate()+ "\n";
            text += "   (수입/지출) 여부: "+ element.getInoutType()+ "\n";
            text += "   카테고리: "+ element.getCategory()+ "\n";
            text += "   금액: "+ element.getAmount()+ "\n";
            text += "   메모: "+ element.getMemo();
            sum += element.getAmount();
            count++;
            IoManager.print(text);
        }
        if(count == 1) {
            IoManager.print("등록된 거래가 존재하지 않습니다.");
        } else{
            IoManager.print("\n총 합계는 " + sum + "원 입니다.\n");
        }
        
        IoManager.print("거래 목록 및 합계를 종료합니다.");
    }

    public void deleteTransfer(){
         IoManager.print("거래 삭제를 선택하셨습니다.");
         int deleteIndex = Integer.parseInt(IoManager.input("삭제할 거래의 번호를 선택하세요(목록 내 번호 기재) > "));

         repository.deleteTransfer(deleteIndex);

         IoManager.print("거래가 삭제되었습니다.");

    }

    public void sumByCategory(){
        IoManager.print("카테고리별 합계를 선택하셨습니다.");
        List<AccountDto> list = repository.totalTransfer();

      if (list == null || list.isEmpty()) {
        IoManager.print("등록된 거래가 존재하지 않습니다.");
      }

        Map<String, Integer> byCategory = list.stream()
        .collect(Collectors.groupingBy(
        AccountDto::getCategory,                  
        Collectors.summingInt(AccountDto::getAmount) 
    ));

    for(Map.Entry<String, Integer> sumByCategory : byCategory.entrySet()){
        String category = sumByCategory.getKey();
        Integer amount = sumByCategory.getValue();

        IoManager.print("카테고리명: " + category + ", 합계: " + amount +"원");
    }

    IoManager.print("카테고리별 합계를 종료합니다.");
       
    }

    public void descAmount(){
        List<AccountDto> list = repository.totalTransfer();
        
        IoManager.print("금액별 내림차순 출력을 선택하셨습니다.");

        if (list == null || list.isEmpty()) {
            IoManager.print("등록된 거래가 존재하지 않습니다.");
        }

        list.sort((a,b) -> Integer.compare(b.getAmount(), a.getAmount()));

        int rank = 1; 
        for(AccountDto element : list){
            String text = "";
            text += rank + "위. 거래일자: "+ element.getDate() + "\n";
            text += "     (수입/지출) 여부: "+ element.getInoutType()+ "\n";
            text += "     카테고리: "+ element.getCategory()+ "\n";
            text += "     금액: "+ element.getAmount()+ "\n";
            text += "     메모: "+ element.getMemo();
            rank ++; 
            IoManager.print(text);
        }

        IoManager.print("금액 내림차순을 종료합니다.");

        }

    public void searchMemo(){
        List<AccountDto> list = repository.totalTransfer();
        IoManager.print("메모 키워드 검색을 선택하셨습니다.");
        String searchMemo = IoManager.input("검색할 메모를 입력해주세요 > ");
        int count = 1;

        if (list == null || list.isEmpty()) {
        IoManager.print("등록된 거래가 없습니다.");
      }
        for(AccountDto element : list) {
            if(element.getMemo().contains(searchMemo)){
                String text = "";
                text += count + ". 거래일자: "+ element.getDate();
                text += ", (수입/지출) 여부: "+ element.getInoutType();
                text += ", 카테고리: "+ element.getCategory();
                text += ", 금액: "+ element.getAmount();
                text += ", 메모: "+ element.getMemo();
                count++;
                IoManager.print(text);
            }
        }
        IoManager.print("메모 키워드 검색을 종료합니다.");
    }
}

