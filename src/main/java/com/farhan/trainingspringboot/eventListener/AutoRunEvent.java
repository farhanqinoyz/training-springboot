package com.farhan.trainingspringboot.eventListener;

import com.farhan.trainingspringboot.entity.ServerLogging;
import com.farhan.trainingspringboot.repository.ServerLoggingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Slf4j
@RequiredArgsConstructor
public class AutoRunEvent {

    private final ServerLoggingRepository serverLoggingRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void updateLoggingWhenRun() {
        log.info("Updating to database.");
        ServerLogging serverLogging = createAndInsertToDatabase();
        log.info(String.format("Updated with id %s.", serverLogging.getId()));
    }

    private ServerLogging createAndInsertToDatabase() {
        ServerLogging serverLogging = new ServerLogging();
        serverLogging.setDate(new Date());
        serverLogging.setDesc("Server is running.");

        serverLoggingRepository.save(serverLogging);
        return serverLogging;
    }
}
