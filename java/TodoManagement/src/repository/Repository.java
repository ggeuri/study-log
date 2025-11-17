package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.TodoDto;

public class Repository {
    List<TodoDto> todoList = new ArrayList<>();
    Map<String,TodoDto> todos = new HashMap<>();
    // Map
    // LocalDate regDate, LocalDate doneDate,boolean isCompleted,String review
    
    public void addTask(String taskTitle,LocalDate regDate,LocalDate doneDate,boolean isCompleted,String review){
        todos.put(taskTitle, new TodoDto(regDate,doneDate,isCompleted,review));

    }

    public Map<String, TodoDto> allTask(){
        return todos;

        }

    
    }


