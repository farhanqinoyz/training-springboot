package com.farhan.trainingspringboot.controller;

import com.farhan.trainingspringboot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class TaskController {

    private final TaskService service;

    @PostMapping("/save/{taskId}")
    public ResponseEntity<String> requestTask(@PathVariable String taskId){
        HttpHeaders headers = new HttpHeaders();
        String responseMessage = "task is created.";
        boolean isSuccess = service.processSaveTask(taskId);
        if(isSuccess){
            return new ResponseEntity<>(responseMessage, headers, HttpStatus.CREATED);
        }
        responseMessage = "please wait for 1 hour for another request.";
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.BAD_REQUEST);
    }
}
