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
        jdbcTemplate.queryForObject("select 1", Integer.class);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "ok");
        return response;
    }
}
