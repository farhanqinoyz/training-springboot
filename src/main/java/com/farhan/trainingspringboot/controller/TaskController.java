package com.farhan.trainingspringboot.controller;

import com.farhan.trainingspringboot.dto.CreateTaskResponseDTO;
import com.farhan.trainingspringboot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.farhan.trainingspringboot.constant.Constant.TaskConstant.TASK_LIMIT_PER_HOUR;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class TaskController {

    private final TaskService service;

    @PostMapping("/save/{taskId}")
    public ResponseEntity<String> requestTask(@PathVariable String taskId){
        HttpHeaders headers = new HttpHeaders();
        String responseMessage;
        CreateTaskResponseDTO response = service.processSaveTask(taskId);
        if(Objects.nonNull(response.getTaskId())){
            responseMessage = String.format("task is created, task id : %s", response.getTaskId());
            return new ResponseEntity<>(responseMessage, headers, HttpStatus.CREATED);
        }
        responseMessage = String.format("please wait for %s hour for another request. " +
                "you can do your next request at : %s", TASK_LIMIT_PER_HOUR, response.getNextSession());
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.BAD_REQUEST);
    }
}
