package com.farhan.trainingspringboot.repository;

import com.farhan.trainingspringboot.entity.ServerLogging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerLoggingRepository extends JpaRepository<ServerLogging, String> {

}
