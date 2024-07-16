package com.fdu.capstone.service;

import com.fdu.capstone.model.Message;
import com.fdu.capstone.model.User;
import com.fdu.capstone.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message getMessageById(Long id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        return messageOptional.orElse(null);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getMessagesBySender(User sender) {
        return messageRepository.findBySender(sender);
    }

    public List<Message> getMessagesByReceiver(User receiver) {
        return messageRepository.findByReceiver(receiver);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
