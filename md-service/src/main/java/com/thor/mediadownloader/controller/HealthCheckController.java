package com.thor.mediadownloader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@MediaDownloaderApiV1
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    private static final String ENTITY = "/health-check";

    @GetMapping(value = ENTITY)
    public ResponseEntity<String> healthCheckEndpoint() {
        logger.info("Aplicação verificada via HealthCheck");

        return ResponseEntity.ok("Health Checked!");
    }

}
