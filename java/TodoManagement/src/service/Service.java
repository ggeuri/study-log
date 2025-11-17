package service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import dto.TodoDto;
import repository.Repository;
import util.IOManager;

public class Service {
    Repository repository = new Repository();

    public void addTask(){
        IOManager.print("[Task 등록 로직]");
        String taskTitle = IOManager.input("할일명 > ");

        while (taskTitle == null || taskTitle.trim().isEmpty()) {
                IOManager.print("올바른 Task명을 입력하세요.");
                taskTitle = IOManager.input("할일명 > ");
            }

            
        String inputRegDate = IOManager.input("등록일(yyyy-mm-dd) > ");

        while (!inputRegDate.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) { //유효성 검사
            IOManager.print("올바른 일자(yyyy-mm-dd)를 입력하세요.");
            inputRegDate = IOManager.input("등록일(yyyy-mm-dd) > ");
        }
        
        LocalDate regDate = LocalDate.parse(inputRegDate);
        
        String inputDoneDate = null;
        inputDoneDate = IOManager.input("완료일(yyyy-mm-dd) > ");
        
        while (inputDoneDate != null &&  // 유효성검사1 - 날짜 
            !inputDoneDate.trim().isEmpty() &&
            !inputDoneDate.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$" )) {
            IOManager.print("올바른 일자(yyyy-mm-dd)를 입력하세요.");
            inputDoneDate = IOManager.input("완료일(yyyy-mm-dd) > ");
        }

        while(inputRegDate.compareTo(inputDoneDate)> 0 &&
            inputDoneDate != null &&  // 유효성검사1 - 날짜 
            !inputDoneDate.trim().isEmpty()){ // 유효성검사2 - 등록일 비교 
            IOManager.print("완료일이 등록일보다 빠릅니다.");
            IOManager.print("다시 입력해주세요.");
            inputDoneDate = IOManager.input("완료일(yyyy-mm-dd) > ");
        }
        
        LocalDate doneDate = null; 
        if(inputDoneDate != null && !inputDoneDate.trim().isEmpty()) {
            doneDate = LocalDate.parse(inputDoneDate);
        }

        String inputCheckComplete = null; 

        inputCheckComplete = IOManager.input("완료 여부(y/n) > ");
        while(!inputCheckComplete.matches("(?i)^(y|n)$")){//대충입력해도 y/n -> true/false로 내가 변환처리.. 
                IOManager.print("y/n 중 하나를 입력하세요.");
                inputCheckComplete = IOManager.input("완료 여부(y/n) > ");
        }

        if(inputCheckComplete.equals("y")||inputCheckComplete.equals("Y")){
            inputCheckComplete = "true";
        } else if (inputCheckComplete.equals("n")||inputCheckComplete.equals("N")){
            inputCheckComplete = "false";}

        boolean checkComplete = Boolean.parseBoolean(inputCheckComplete);

        String inputReview = IOManager.input("후기 > ");
       
        String review = null;
        if(inputReview != null && !inputReview.trim().isEmpty()) {
            review = inputReview;
        }

        repository.addTask(taskTitle, regDate, doneDate, checkComplete, review);

        IOManager.print("할일이 등록되었습니다.");
        
    }


    public void allTask(){
        IOManager.print("[Task 목록 출력]");
        Map<String,TodoDto> todos = repository.allTask();

        Set<String> keys = todos.keySet();
        
        for (String key : keys) {
            TodoDto todoDto = todos.get(key);
            String text = "";
            text += "Task명: " + key;
            text += ", 등록일: " + todoDto.getRegDate();
            text += ", 완료여부: " + todoDto.getIsCompleted();
            text += ", 완료일: " + todoDto.getDoneDate();
            text += ", 후기: " + todoDto.getReview();
                
            IOManager.print(text);
        }

        IOManager.print("총 Task 수 : " + keys.size());

    }

    public void markAsComplete(){
        IOManager.print("[Task 상태 변경 로직]");
        Map<String,TodoDto> todos = repository.allTask();
        String replaceTask = null;

        replaceTask = IOManager.input("변경할 Task명 > ");

        while(!todos.containsKey(replaceTask)){
                IOManager.print("Task명이 존재하지 않습니다. 다시입력해주세요. ");
                replaceTask = IOManager.input("변경할 Task명 > ");
            }         
            
        todos.get(replaceTask).setIsCompleted(true);
        todos.get(replaceTask).setDoneDate(LocalDate.now());
        IOManager.print("\"[" + replaceTask + "]\" :  완료로 변경 처리되었습니다.");
        }
       
        
   

    public void showIncompleteTasks(){
        IOManager.print("[미완료 Task 출력]");
        Map<String,TodoDto> todos = repository.allTask();
        Set<String> keys = todos.keySet();
        int count = 0;
        
        for (String key : keys) {
            TodoDto todoDto = todos.get(key);
            if(!todoDto.getIsCompleted()){
            String text = "";
            text += "task명: " + key;
            text += ", 등록일: " + todoDto.getRegDate();
            text += ", 완료여부: " + todoDto.getIsCompleted();
            text += ", 완료일: " + todoDto.getDoneDate();
            text += ", 후기: " + todoDto.getReview();
            count++;
                
            IOManager.print(text);
        }
    }
    IOManager.print("미완료된 Task 수: " + count);
    }

    public void showCompleteTasks(){
        IOManager.print("[완료 Task 출력]");
        Map<String,TodoDto> todos = repository.allTask();
        Set<String> keys = todos.keySet();
        int count = 0;
        
        for (String key : keys) {
            TodoDto todoDto = todos.get(key);
            if(todoDto.getIsCompleted()){
            String text = "";
            text += "Task명: " + key;
            text += ", 등록일: " + todoDto.getRegDate();
            text += ", 완료여부: " + todoDto.getIsCompleted();
            text += ", 완료일: " + todoDto.getDoneDate();
            text += ", 후기: " + todoDto.getReview();
            count++;
            IOManager.print(text);
        }
    }
    IOManager.print("완료된 Task 수: " + count);
    }

    public void searchTitleKeyword(){
    IOManager.print("[Task명 검색]");
      Map<String,TodoDto> todos = repository.allTask();
      String searchKeyword = IOManager.input("검색할 Task명 > ");
      int count = 0 ;

      Set<String> keys = todos.keySet();
      
      for (String key : keys) {
          TodoDto todoDto = todos.get(key);
          if(key.equals(searchKeyword)){
            String text = "";
            text += (count+1) + ". Task명: " + key;
            text += ", 등록일: " + todoDto.getRegDate();
            text += ", 완료여부: " + todoDto.getIsCompleted();
            text += ", 완료일: " + todoDto.getDoneDate();
            text += ", 후기: " + todoDto.getReview();
            count++; 
            IOManager.print(text);
            }
        }
        IOManager.print("검색된 Task 수: " + count);
    }


    public void deleteTasks(){
     IOManager.print("[Task 삭제]");
      Map<String,TodoDto> todos = repository.allTask();
      String deleteKeyword = IOManager.input("삭제할 Task명 > ");

      todos.remove(deleteKeyword);


      IOManager.print("\"[" + deleteKeyword + "]\" :  삭제되었습니다.");
      IOManager.print("남은 Task 수: " + todos.size());

    }




}
