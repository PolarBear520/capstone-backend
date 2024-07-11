package com.fdu.capstone.service;

import com.fdu.capstone.model.User;
import com.fdu.capstone.repository.UserRepository;
import com.fdu.capstone.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        // 对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 对其他敏感数据进行加密，例如email和phoneNumber
        try {
            String encryptedEmail = EncryptionUtil.encrypt(user.getEmail());
            user.setEmail(encryptedEmail);

            String encryptedPhoneNumber = EncryptionUtil.encrypt(user.getPhoneNumber());
            user.setPhoneNumber(encryptedPhoneNumber);
        } catch (Exception e) {
            logger.error("Error encrypting user data", e);
        }

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        // 先对email进行加密，然后查找用户
        try {
            String encryptedEmail = EncryptionUtil.encrypt(email);
            User user = userRepository.findByEmail(encryptedEmail);

            // 对其他敏感数据进行解密，例如phoneNumber
            if (user != null) {
                String decryptedPhoneNumber = EncryptionUtil.decrypt(user.getPhoneNumber());
                user.setPhoneNumber(decryptedPhoneNumber);
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
                // 解密敏感数据
                String decryptedEmail = EncryptionUtil.decrypt(user.getEmail());
                user.setEmail(decryptedEmail);

                String decryptedPhoneNumber = EncryptionUtil.decrypt(user.getPhoneNumber());
                user.setPhoneNumber(decryptedPhoneNumber);
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
                // 解密敏感数据
                String decryptedEmail = EncryptionUtil.decrypt(user.getEmail());
                user.setEmail(decryptedEmail);

                String decryptedPhoneNumber = EncryptionUtil.decrypt(user.getPhoneNumber());
                user.setPhoneNumber(decryptedPhoneNumber);
            } catch (Exception e) {
                logger.error("Error decrypting user data", e);
            }
        }
        return users;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
