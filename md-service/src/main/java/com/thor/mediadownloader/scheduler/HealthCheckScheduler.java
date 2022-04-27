package com.thor.mediadownloader.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class HealthCheckScheduler {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckScheduler.class);

    @Scheduled(fixedRate=1500000)
    public void healthCheck() {
        logger.info("Heroku Health Check");
    }
}
