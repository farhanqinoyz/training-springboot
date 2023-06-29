package com.farhan.trainingspringboot.repository;

import com.farhan.trainingspringboot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {

}
