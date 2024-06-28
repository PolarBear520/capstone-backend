package com.fdu.capstone.service;

import com.fdu.capstone.model.Message;
import com.fdu.capstone.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessagesByUserId(Long userId) {
        return messageRepository.findByReceiverId(userId);
    }

    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }
}
