package com.fdu.capstone.repository;

import com.fdu.capstone.model.Message;
import com.fdu.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);
}
