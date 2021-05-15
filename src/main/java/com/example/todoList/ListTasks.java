package com.example.todoList;

import lombok.Data;

@Data
public class ListTasks { //Данные о заданиях
    private String task;
    private long id;
    private boolean done;

}
