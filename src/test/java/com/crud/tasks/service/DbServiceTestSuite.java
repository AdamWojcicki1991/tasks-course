package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {
    @Autowired
    private DbService dbService;

    @Test
    public void shouldGetAllTasks() {
        //GIVEN
        Task task = new Task(1L, "Title", "Content");
        long taskId = dbService.saveTask(task).getId();
        //WHEN
        List<Task> tasks = dbService.getAllTasks();
        //THEN
        assertEquals(1, tasks.size());
        //CLEANUP
        dbService.deleteTaskByID(taskId);
    }

    @Test
    public void shouldGetTaskById() {
        //GIVEN
        Task task = new Task(1L, "Title", "Content");
        long taskId = dbService.saveTask(task).getId();
        //WHEN
        Task taskFromDatabase = dbService.getTaskById(taskId).get();
        Long taskIdFromDatabase = taskFromDatabase.getId();
        //THEN
        assertEquals(taskIdFromDatabase, taskFromDatabase.getId());
        assertEquals("Title", taskFromDatabase.getTitle());
        assertEquals("Content", taskFromDatabase.getContent());
        //CLEANUP
        dbService.deleteTaskByID(taskId);
    }

    @Test
    public void shouldSaveTask() {
        //GIVEN
        Task task = new Task(1L, "Title", "Content");
        //WHEN
        long taskId = dbService.saveTask(task).getId();
        //THEN
        assertNotEquals(0, taskId);
        //CLEANUP
        dbService.deleteTaskByID(taskId);
    }

    @Test
    public void shouldDeleteTaskById() {
        //GIVEN
        Task task = new Task(1L, "Title", "Content");
        long taskId = dbService.saveTask(task).getId();
        //WHEN
        dbService.deleteTaskByID(taskId);
        //THEN
        assertFalse(dbService.getTaskById(taskId).isPresent());
    }
}
