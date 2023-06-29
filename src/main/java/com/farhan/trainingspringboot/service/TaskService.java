package com.farhan.trainingspringboot.service;

import com.farhan.trainingspringboot.entity.Task;
import com.farhan.trainingspringboot.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public boolean processSaveTask(String userId){
        //TODO: Check to db is userId already created on table task
        this.save(userId);
        return true;
    }

    private void save(String userId){
        String taskId = this.createTaskId(userId);
        Task task = new Task();
        task.setId(taskId);
        task.setStatus("1");
        repository.save(task);
    }

    private String createTaskId(String userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        String formattedDate = sdf.format(date);
        return formattedDate.concat(userId).substring(0,40);
    }
}
