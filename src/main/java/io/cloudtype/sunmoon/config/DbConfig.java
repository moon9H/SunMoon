package io.cloudtype.sunmoon.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Profile("db")
@Configuration
public class DbConfig {

    @Bean
    public DataSource dataSource(
        @Value("${SPRING_DATASOURCE_URL}") String url,
        @Value("${SPRING_DATASOURCE_USERNAME}") String username,
        @Value("${SPRING_DATASOURCE_PASSWORD}") String password
    ) {
        HikariDataSource dataSource = DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .url(url)
            .username(username)
            .password(password)
            .driverClassName("org.postgresql.Driver")
            .build();

        dataSource.setMaximumPoolSize(3);
        dataSource.setMinimumIdle(1);
        dataSource.setConnectionTimeout(10000);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
