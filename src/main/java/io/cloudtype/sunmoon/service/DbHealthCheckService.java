package io.cloudtype.sunmoon.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Profile("db")
@Service
public class DbHealthCheckService {

    private final JdbcTemplate jdbcTemplate;

    public DbHealthCheckService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> check() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "ok");
        response.put("database", jdbcTemplate.queryForObject("select current_database()", String.class));
        response.put("currentUser", jdbcTemplate.queryForObject("select current_user", String.class));
        response.put("serverTime", jdbcTemplate.queryForObject("select now()::text", String.class));
        return response;
    }
}
