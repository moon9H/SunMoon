package io.cloudtype.sunmoon.dto.conversation;

import java.time.LocalDate;
import java.util.List;

public class ConversationDetail {

    private final Long id;
    private final String title;
    private final LocalDate memoryDate;
    private final List<MessageDetail> messages;

    public ConversationDetail(Long id, String title, LocalDate memoryDate, List<MessageDetail> messages) {
        this.id = id;
        this.title = title;
        this.memoryDate = memoryDate;
        this.messages = messages;
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

    public List<MessageDetail> getMessages() {
        return messages;
    }
}
