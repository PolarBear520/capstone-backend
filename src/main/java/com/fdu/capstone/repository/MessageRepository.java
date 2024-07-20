package com.fdu.capstone.repository;

import com.fdu.capstone.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderId(Long senderId);
//    List<Message> findByReceiverId(Long receiverId);
    List<Message> findByConversationId(Long conversationId);
}
