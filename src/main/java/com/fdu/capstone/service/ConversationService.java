package com.fdu.capstone.service;

import com.fdu.capstone.model.Conversation;
import com.fdu.capstone.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public Conversation createConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public Conversation getConversationById(Long id) {
        Optional<Conversation> conversationOptional = conversationRepository.findById(id);
        return conversationOptional.orElse(null);
    }

    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }

    public void deleteConversation(Long id) {
        conversationRepository.deleteById(id);
    }
}
