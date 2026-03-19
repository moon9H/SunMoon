package io.cloudtype.sunmoon.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.sunmoon.service.DbHealthCheckService;

@Profile("db")
@RestController
public class DbPingController {

    private final DbHealthCheckService dbHealthCheckService;

    public DbPingController(DbHealthCheckService dbHealthCheckService) {
        this.dbHealthCheckService = dbHealthCheckService;
    }

    @GetMapping("/db/ping")
    public ResponseEntity<Map<String, Object>> ping() {
        try {
            return ResponseEntity.ok(dbHealthCheckService.check());
        } catch (Exception exception) {
            Map<String, Object> response = new LinkedHashMap<>();
            Throwable rootCause = exception;

            while (rootCause.getCause() != null) {
                rootCause = rootCause.getCause();
            }

            response.put("status", "error");
            response.put("errorType", exception.getClass().getSimpleName());
            response.put("message", exception.getMessage());
            response.put("rootCauseType", rootCause.getClass().getSimpleName());
            response.put("rootCauseMessage", rootCause.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
