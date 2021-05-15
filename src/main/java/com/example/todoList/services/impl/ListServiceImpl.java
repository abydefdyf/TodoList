package com.example.todoList.services.impl;

import com.example.todoList.ListTasks;
import com.example.todoList.services.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ListTasks> getList() { //Вывод всего списка заданий
        var sql = "SELECT id, list, done\n" +
                "\tFROM public.todolist";
        return jdbcTemplate.query(sql, new RowMapper <ListTasks>() {
            @Override
            public ListTasks mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                var listTasks = new ListTasks();
                listTasks.setTask(resultSet.getString("list"));
                listTasks.setId(resultSet.getLong("id"));
                listTasks.setDone(resultSet.getBoolean("done"));
                return listTasks;
            }
        });
    }

    @Override
    public void addTask(String list){ //Добавление задания
        var sqlInsert = "INSERT INTO public.todoList(\n" +
                "\tlist)\n" +
                "\tVALUES (?)";
        jdbcTemplate.update(sqlInsert, new Object[] {
                list
        });
    }

    @Override
    public void deleteList() { //Удаление всего списка заданий
        var sql = "TRUNCATE TABLE public.todolist";
        jdbcTemplate.update(sql);

    }

    @Override
    public void deleteTask(long id) { //Удаление конекретного задания
        var sql = "DELETE FROM public.todolist\n" +
                "\tWHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{
                id
        });
    }

    @Override
    public void doneTask(long id, boolean done) { //Отметить как выполненное задание
            var sqlUpdate = "UPDATE public.todolist\n" +
                    "\tSET done=true\n" +
                    "\tWHERE id = ? ";
            jdbcTemplate.update(sqlUpdate, new Object[]{
                    id
            });
    }
}
