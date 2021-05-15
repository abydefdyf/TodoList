package com.example.todoList.controller;

import com.example.todoList.services.ListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class TodoController {

    @Autowired
    private ListService listService;

    @GetMapping(path = "/todo.html") //Вывод всего списка заданий
    public String todoPage(Model model) {
        model.addAttribute("tasks", listService.getList());
        return "todo";
    }
    @GetMapping(path = "/deleteList.html") //Удаление всего списка заданий
    public String deleteListPage() {
        listService.deleteList();
        return "redirect:/todo.html";
    }
    @GetMapping(path = "/deleteTask.html") //Удаление конекретного задания
    public String deleteTaskPage(@RequestParam(name = "deleteTask") long taskId) {
        listService.deleteTask(taskId);
        return "redirect:/todo.html";
    }
    @GetMapping(path = "/doneTask.html")//Отметить как выполненное задание
    public String doneTaskPage(@RequestParam(name = "taskId") long taskId, @RequestParam(name = "doneTask") boolean doneTask, Model model){
        if (doneTask){
            model.addAttribute("errorDoneTask", true);
            return "/todo.html";
        }
        listService.doneTask(taskId, doneTask);
        return "redirect:/todo.html";
    }
    @PostMapping(path = "/todo.html") //Добавление задания
    public String addTaskPage(@RequestParam(name = "task") String task, Model model){
        if (task.isEmpty()){
            model.addAttribute("errorTextNotFound", true);
            return "/todo.html";
        }
            listService.addTask(task);
            return "redirect:/todo.html";
    }


}
