package io.cloudtype.sunmoon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.cloudtype.sunmoon.dto.conversation.ConversationDetail;
import io.cloudtype.sunmoon.dto.conversation.ConversationSummary;
import io.cloudtype.sunmoon.dto.conversation.MessageDetail;

@Profile("db")
@Repository
public class ConversationQueryRepository {

    private final JdbcTemplate jdbcTemplate;

    public ConversationQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ConversationSummary> findConversationSummaries() {
        String sql = """
            SELECT
                c.id,
                c.title,
                c.memory_date,
                COUNT(m.id) AS message_count
            FROM conversation c
            LEFT JOIN message m ON m.conversation_id = c.id
            GROUP BY c.id, c.title, c.memory_date
            ORDER BY c.memory_date DESC, c.id DESC
            """;

        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) -> new ConversationSummary(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getDate("memory_date").toLocalDate(),
                resultSet.getInt("message_count")
            )
        );
    }

    public Optional<ConversationDetail> findConversationDetail(Long conversationId) {
        String conversationSql = """
            SELECT
                c.id,
                c.title,
                c.memory_date
            FROM conversation c
            WHERE c.id = ?
            """;

        List<ConversationDetail> conversations = jdbcTemplate.query(
            conversationSql,
            (resultSet, rowNum) -> new ConversationDetail(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getDate("memory_date").toLocalDate(),
                findMessagesByConversationId(resultSet.getLong("id"))
            ),
            conversationId
        );

        return conversations.stream().findFirst();
    }

    private List<MessageDetail> findMessagesByConversationId(Long conversationId) {
        String messageSql = """
            SELECT
                m.id,
                m.author,
                m.content,
                m.display_time,
                m.sequence
            FROM message m
            WHERE m.conversation_id = ?
            ORDER BY m.sequence ASC
            """;

        return jdbcTemplate.query(
            messageSql,
            (resultSet, rowNum) -> new MessageDetail(
                resultSet.getLong("id"),
                resultSet.getString("author"),
                resultSet.getString("content"),
                resultSet.getString("display_time"),
                resultSet.getInt("sequence")
            ),
            conversationId
        );
    }
}
