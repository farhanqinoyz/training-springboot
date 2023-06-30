package com.farhan.trainingspringboot.service;

import com.farhan.trainingspringboot.dto.CreateTaskResponseDTO;
import com.farhan.trainingspringboot.entity.Task;
import com.farhan.trainingspringboot.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.farhan.trainingspringboot.constant.Constant.TaskConstant.TASK_LIMIT_PER_HOUR;
import static com.farhan.trainingspringboot.constant.Constant.TaskStatus.ON_PROGRESS;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository repository;

    public CreateTaskResponseDTO processSaveTask(String userId){
        CreateTaskResponseDTO response = new CreateTaskResponseDTO();
        Task taskWithinLimitHour = repository
                .findTaskWithCreatedDateWhatHourAmdUserId(userId, TASK_LIMIT_PER_HOUR);
        if(Objects.nonNull(taskWithinLimitHour)){
            Date nextSessionDate = this.getNextSessionDate(taskWithinLimitHour);
            String nextSession = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(nextSessionDate);
            response.setNextSession(nextSession);
            return response;
        }
        String taskId = this.save(userId);
        response.setTaskId(taskId);
        return response;
    }

    private Date getNextSessionDate(Task taskWithinLimitHour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(taskWithinLimitHour.getCreatedDate());
        cal.add(Calendar.HOUR_OF_DAY, TASK_LIMIT_PER_HOUR);
        return cal.getTime();
    }

    private String save(String userId){
        String taskId = this.createTaskId(userId);
        Task task = new Task();
        task.setId(taskId);
        task.setStatus(ON_PROGRESS);
        repository.save(task);
        return taskId;
    }

    private String createTaskId(String userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        String formattedDate = sdf.format(date);
        return formattedDate.concat(userId).substring(0,40);
    }
}
