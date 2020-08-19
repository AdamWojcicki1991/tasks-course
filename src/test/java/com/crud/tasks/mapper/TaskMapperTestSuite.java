package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTask() {
        //GIVEN
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Long taskDtoId = taskDto.getId();
        String titleDto = taskDto.getTitle();
        String contentDto = taskDto.getContent();
        //WHEN
        Task task = taskMapper.mapToTask(taskDto);
        Long taskId = task.getId();
        String title = task.getTitle();
        String content = task.getContent();
        //THEN
        assertEquals(taskDtoId, taskId);
        assertEquals(titleDto, title);
        assertEquals(contentDto, content);
    }

    @Test
    public void shouldMapToTaskDto() {
        //GIVEN
        Task task = new Task(1L, "Title", "Content");
        Long taskId = task.getId();
        String title = task.getTitle();
        String content = task.getContent();
        //WHEN
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        Long taskDtoId = taskDto.getId();
        String titleDto = taskDto.getTitle();
        String contentDto = taskDto.getContent();
        //THEN
        assertEquals(taskId, taskDtoId);
        assertEquals(title, titleDto);
        assertEquals(content, contentDto);
    }

    @Test
    public void shouldMapToTaskDtoList() {
        //GIVEN
        Task task = new Task(1L, "Title", "Content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        //WHEN
        List<TaskDto> tasksDto = taskMapper.mapToTaskDtoList(tasks);
        //THEN
        assertEquals(1, tasksDto.size());
        assertEquals(1, (long) tasksDto.get(0).getId());
        assertEquals("Title", tasksDto.get(0).getTitle());
        assertEquals("Content", tasksDto.get(0).getContent());
    }
}
