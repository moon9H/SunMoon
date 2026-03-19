package io.cloudtype.sunmoon.dto.conversation;

import java.time.LocalDate;

public class ConversationSummary {

    private final Long id;
    private final String title;
    private final LocalDate memoryDate;
    private final int messageCount;

    public ConversationSummary(Long id, String title, LocalDate memoryDate, int messageCount) {
        this.id = id;
        this.title = title;
        this.memoryDate = memoryDate;
        this.messageCount = messageCount;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getMemoryDate() {
        return memoryDate;
    }

    public int getMessageCount() {
        return messageCount;
    }
}
