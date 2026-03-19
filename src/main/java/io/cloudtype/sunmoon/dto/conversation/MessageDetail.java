package io.cloudtype.sunmoon.dto.conversation;

public class MessageDetail {

    private final Long id;
    private final String author;
    private final String content;
    private final String displayTime;
    private final int sequence;

    public MessageDetail(Long id, String author, String content, String displayTime, int sequence) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.displayTime = displayTime;
        this.sequence = sequence;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDisplayTime() {
        return displayTime;
    }

    public int getSequence() {
        return sequence;
    }
}
