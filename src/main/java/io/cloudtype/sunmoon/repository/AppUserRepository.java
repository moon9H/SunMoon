package io.cloudtype.sunmoon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.cloudtype.sunmoon.auth.AppUser;

@Profile("db")
@Repository
public class AppUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<AppUser> findByLoginId(String loginId) {
        String sql = """
            SELECT
                au.id,
                au.login_id,
                au.password_hash,
                au.display_name,
                au.viewer_id,
                au.enabled
            FROM app_user au
            WHERE au.login_id = ?
            """;

        List<AppUser> users = jdbcTemplate.query(
            sql,
            (resultSet, rowNum) -> new AppUser(
                resultSet.getLong("id"),
                resultSet.getString("login_id"),
                resultSet.getString("password_hash"),
                resultSet.getString("display_name"),
                resultSet.getString("viewer_id"),
                resultSet.getBoolean("enabled")
            ),
            loginId
        );

        return users.stream().findFirst();
    }
}
