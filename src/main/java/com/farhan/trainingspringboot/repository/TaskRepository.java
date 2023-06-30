package com.farhan.trainingspringboot.repository;

import com.farhan.trainingspringboot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, String> {

    @Query(value = "select * from task " +
            "where created_date >= DATE_SUB(NOW(), INTERVAL :?2 HOUR) " +
            "and user_id = :?1", nativeQuery = true)
    Task findTaskWithCreatedDateWhatHourAmdUserId(String userId, int hour);
}
