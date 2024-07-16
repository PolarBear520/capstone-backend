package com.fdu.capstone.service;

import com.fdu.capstone.model.User;
import com.fdu.capstone.repository.UserRepository;
import com.fdu.capstone.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());

        try {
            user.setEmail(encryptData(user.getEmail()));
            user.setPhoneNumber(encryptData(user.getPhoneNumber()));
        } catch (Exception e) {
            logger.error("Error encrypting user data", e);
        }

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        try {
            String encryptedEmail = encryptData(email);
            User user = userRepository.findByEmail(encryptedEmail);
            if (user != null) {
                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
            }
            return user;
        } catch (Exception e) {
            logger.error("Error finding user by email", e);
            return null;
        }
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            try {
                user.setEmail(decryptData(user.getEmail()));
                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
            } catch (Exception e) {
                logger.error("Error decrypting user data", e);
            }
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            try {
                user.setEmail(decryptData(user.getEmail()));
                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
            } catch (Exception e) {
                logger.error("Error decrypting user data", e);
            }
        }
        return users;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private String encryptData(String data) throws Exception {
        return data != null ? EncryptionUtil.encrypt(data) : null;
    }

    private String decryptData(String data) throws Exception {
        return data != null ? EncryptionUtil.decrypt(data) : null;
    }
}

