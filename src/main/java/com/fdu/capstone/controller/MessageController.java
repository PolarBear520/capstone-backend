package com.fdu.capstone.controller;

import com.fdu.capstone.model.Message;
import com.fdu.capstone.model.User;
import com.fdu.capstone.service.MessageService;
import com.fdu.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Message message = messageService.getMessageById(id);
        return message != null ? ResponseEntity.ok(message) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/sender/{senderId}")
    public ResponseEntity<List<Message>> getMessagesBySender(@PathVariable Long senderId) {
        User sender = userService.getUserById(senderId);
        if (sender != null) {
            List<Message> messages = messageService.getMessagesBySender(sender);
            return ResponseEntity.ok(messages);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesByReceiver(@PathVariable Long receiverId) {
        User receiver = userService.getUserById(receiverId);
        if (receiver != null) {
            List<Message> messages = messageService.getMessagesByReceiver(receiver);
            return ResponseEntity.ok(messages);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        try {
            messageService.deleteMessage(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
