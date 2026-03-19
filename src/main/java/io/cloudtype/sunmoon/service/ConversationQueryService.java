package io.cloudtype.sunmoon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.cloudtype.sunmoon.dto.conversation.ConversationDetail;
import io.cloudtype.sunmoon.dto.conversation.ConversationSummary;
import io.cloudtype.sunmoon.repository.ConversationQueryRepository;

@Profile("db")
@Service
public class ConversationQueryService {

    private final ConversationQueryRepository conversationQueryRepository;

    public ConversationQueryService(ConversationQueryRepository conversationQueryRepository) {
        this.conversationQueryRepository = conversationQueryRepository;
    }

    public List<ConversationSummary> getConversationSummaries() {
        return conversationQueryRepository.findConversationSummaries();
    }

    public Optional<ConversationDetail> getConversationDetail(Long conversationId) {
        return conversationQueryRepository.findConversationDetail(conversationId);
    }
}
