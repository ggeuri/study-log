package dto;

import java.time.LocalDate;

public class TodoDto {
    private LocalDate regDate ; 
    private LocalDate doneDate;
    private boolean isCompleted; 
    private String review;

    public TodoDto(){}

    public TodoDto(LocalDate regDate, LocalDate doneDate,boolean isCompleted,String review){
        this.regDate = regDate;
        this.doneDate = doneDate;
        this.isCompleted = isCompleted;
        this.review = review;
    }

    public TodoDto(LocalDate regDate,boolean isCompleted){
        this.regDate = regDate;
        this.isCompleted = isCompleted;
    }
    
    public void setRegDate(LocalDate regDate){
        this.regDate = regDate;
    }
    
    public void setDoneDate(LocalDate doneDate){
        this.doneDate = doneDate;
    }
    
    public void setIsCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }
    
    public void setReview(String review){
        this.review = review;
    }

    
    public LocalDate getRegDate(){
        return regDate;
    }
    public LocalDate getDoneDate(){
        return doneDate;
    }
    public boolean getIsCompleted(){
        return isCompleted; 
    }
    public String getReview(){
        return review; 
    }
    

    

}
