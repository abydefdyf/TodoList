package com.example.todoList.services;

import com.example.todoList.ListTasks;

import java.util.List;

public interface ListService {

    /**
     * Получить список всех заданий
     *
     * @return
     */
    List<ListTasks> getList();

    /**
     * Добавляет задание в систему
     *
     * @param
     */
    void addTask(String list);

    /**
     * Удаляем список из системы
     */
    void deleteList();

    /**
     * Удаляем задание из системы
     */
    void deleteTask(long id);

    /**
     * Отмечаем выполненное задание
     */
    void doneTask(long id, boolean done);
}
