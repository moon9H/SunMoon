package io.cloudtype.sunmoon.controller;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.cloudtype.sunmoon.dto.conversation.ConversationDetail;
import io.cloudtype.sunmoon.dto.conversation.ConversationSummary;
import io.cloudtype.sunmoon.service.ConversationQueryService;

@Profile("db")
@RestController
@RequestMapping("/api/conversations")
public class ConversationApiController {

    private final ConversationQueryService conversationQueryService;

    public ConversationApiController(ConversationQueryService conversationQueryService) {
        this.conversationQueryService = conversationQueryService;
    }

    @GetMapping
    public List<ConversationSummary> getConversations() {
        return conversationQueryService.getConversationSummaries();
    }

    @GetMapping("/{conversationId}")
    public ConversationDetail getConversationDetail(@PathVariable Long conversationId) {
        return conversationQueryService.getConversationDetail(conversationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
