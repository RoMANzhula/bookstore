package org.romanzhula.bookstore.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerService {

    @Scheduled(fixedRate = 2000)
    public void logSomething() {
        log.info("I live. I here!");
    }
}
