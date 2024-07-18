////package com.fdu.capstone.service;
////
////import com.fdu.capstone.config.RSAKeyConfig;
////import com.fdu.capstone.model.User;
////import com.fdu.capstone.repository.UserRepository;
////import com.fdu.capstone.util.EncryptionUtil;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////
////import java.time.LocalDateTime;
////import java.util.List;
////import java.util.Optional;
////
////@Service
////public class UserService {
////
////    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private PasswordEncoder passwordEncoder;
////
////    @Autowired
////    private RSAKeyConfig rsaKeyConfig;
////
////    public User createUser(User user) {
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        user.setRegistrationDate(LocalDateTime.now());
////
////        try {
////            user.setEmail(encryptData(user.getEmail()));
////            user.setPhoneNumber(encryptData(user.getPhoneNumber()));
////        } catch (Exception e) {
////            logger.error("Error encrypting user data", e);
////        }
////
////        return userRepository.save(user);
////    }
////
////    public User findByEmail(String email) {
////        try {
////            String encryptedEmail = encryptData(email);
////            User user = userRepository.findByEmail(encryptedEmail);
////            if (user != null) {
////                user.setEmail(decryptData(user.getEmail()));
////                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
////            }
////            return user;
////        } catch (Exception e) {
////            logger.error("Error finding user by email", e);
////            return null;
////        }
////    }
////
////    public User getUserById(Long id) {
////        Optional<User> userOptional = userRepository.findById(id);
////        if (userOptional.isPresent()) {
////            User user = userOptional.get();
////            try {
////                user.setEmail(decryptData(user.getEmail()));
////                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
////            } catch (Exception e) {
////                logger.error("Error decrypting user data", e);
////            }
////            return user;
////        }
////        return null;
////    }
////
////    public List<User> getAllUsers() {
////        List<User> users = userRepository.findAll();
////        for (User user : users) {
////            try {
////                user.setEmail(decryptData(user.getEmail()));
////                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
////            } catch (Exception e) {
////                logger.error("Error decrypting user data", e);
////            }
////        }
////        return users;
////    }
////
////    public void deleteUser(Long id) {
////        userRepository.deleteById(id);
////    }
////
////    private String encryptData(String data) throws Exception {
////        String aesKey = EncryptionUtil.generateAESKey();
////        String encryptedData = EncryptionUtil.encryptAES(data, aesKey);
////        String encryptedAESKey = EncryptionUtil.encryptRSA(aesKey, rsaKeyConfig.getPublicKey());
////        return encryptedAESKey + ":" + encryptedData;
////    }
////
////    private String decryptData(String data) throws Exception {
////        String[] parts = data.split(":");
////        String encryptedAESKey = parts[0];
////        String encryptedData = parts[1];
////        String aesKey = EncryptionUtil.decryptRSA(encryptedAESKey, rsaKeyConfig.getPrivateKey());
////        return EncryptionUtil.decryptAES(encryptedData, aesKey);
////    }
////}
//
//package com.fdu.capstone.service;
//
//import com.fdu.capstone.config.RSAKeyConfig;
//import com.fdu.capstone.model.User;
//import com.fdu.capstone.repository.UserRepository;
//import com.fdu.capstone.util.EncryptionUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private RSAKeyConfig rsaKeyConfig;
//
//    public User createUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRegistrationDate(LocalDateTime.now());
//
//        try {
//            user.setEmail(encryptData(user.getEmail()));
//            user.setPhoneNumber(encryptData(user.getPhoneNumber()));
//        } catch (Exception e) {
//            logger.error("Error encrypting user data", e);
//        }
//
//        return userRepository.save(user);
//    }
//
//    public User findByEmail(String email) {
//        try {
//            String encryptedEmail = encryptData(email);
//            User user = userRepository.findByEmail(encryptedEmail);
//            if (user != null) {
//                user.setEmail(decryptData(user.getEmail()));
//                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
//            }
//            return user;
//        } catch (Exception e) {
//            logger.error("Error finding user by email", e);
//            return null;
//        }
//    }
//
//    public User getUserById(Long id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            try {
//                user.setEmail(decryptData(user.getEmail()));
//                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
//            } catch (Exception e) {
//                logger.error("Error decrypting user data", e);
//            }
//            return user;
//        }
//        return null;
//    }
//
//    public List<User> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            try {
//                user.setEmail(decryptData(user.getEmail()));
//                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
//            } catch (Exception e) {
//                logger.error("Error decrypting user data", e);
//            }
//        }
//        return users;
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    public boolean checkPassword(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//
//    private String encryptData(String data) throws Exception {
//        if (data == null) {
//            return null;
//        }
//        String aesKey = EncryptionUtil.generateAESKey();
//        String encryptedData = EncryptionUtil.encryptAES(data, aesKey);
//        String encryptedAESKey = EncryptionUtil.encryptRSA(aesKey, rsaKeyConfig.getPublicKey());
//        return encryptedAESKey + ":" + encryptedData;
//    }
//
//    private String decryptData(String data) throws Exception {
//        if (data == null) {
//            return null;
//        }
//        String[] parts = data.split(":");
//        if (parts.length != 2) {
//            throw new IllegalArgumentException("Invalid encrypted data format");
//        }
//        String encryptedAESKey = parts[0];
//        String encryptedData = parts[1];
//        String aesKey = EncryptionUtil.decryptRSA(encryptedAESKey, rsaKeyConfig.getPrivateKey());
//        return EncryptionUtil.decryptAES(encryptedData, aesKey);
//    }
//}
//
//package com.fdu.capstone.service;
//
//import com.fdu.capstone.config.RSAKeyConfig;
//import com.fdu.capstone.model.User;
//import com.fdu.capstone.repository.UserRepository;
//import com.fdu.capstone.util.EncryptionUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private RSAKeyConfig rsaKeyConfig;
//
//    public User createUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRegistrationDate(LocalDateTime.now());
//
//        try {
//            user.setEmail(encryptData(user.getEmail()));
//            user.setPhoneNumber(encryptData(user.getPhoneNumber()));
//        } catch (Exception e) {
//            logger.error("Error encrypting user data", e);
//        }
//
//        return userRepository.save(user);
//    }
//
//    public boolean authenticateUser(String email, String password) {
//        try {
//            String encryptedEmail = encryptData(email);
//            User user = userRepository.findByEmail(encryptedEmail);
//            if (user != null) {
//                return passwordEncoder.matches(password, user.getPassword());
//            }
//        } catch (Exception e) {
//            logger.error("Error authenticating user", e);
//        }
//        return false;
//    }
//
//    public User findByEmail(String email) {
//        try {
//            String encryptedEmail = encryptData(email);
//            User user = userRepository.findByEmail(encryptedEmail);
//            if (user != null) {
//                user.setEmail(decryptData(user.getEmail()));
//                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
//            }
//            return user;
//        } catch (Exception e) {
//            logger.error("Error finding user by email", e);
//            return null;
//        }
//    }
//
//    public User getUserById(Long id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            try {
//                user.setEmail(decryptData(user.getEmail()));
//                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
//            } catch (Exception e) {
//                logger.error("Error decrypting user data", e);
//            }
//            return user;
//        }
//        return null;
//    }
//
//    public List<User> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            try {
//                user.setEmail(decryptData(user.getEmail()));
//                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
//            } catch (Exception e) {
//                logger.error("Error decrypting user data", e);
//            }
//        }
//        return users;
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    private String encryptData(String data) throws Exception {
//        String aesKey = EncryptionUtil.generateAESKey();
//        String encryptedData = EncryptionUtil.encryptAES(data, aesKey);
//        String encryptedAESKey = EncryptionUtil.encryptRSA(aesKey, rsaKeyConfig.getPublicKey());
//        return encryptedAESKey + ":" + encryptedData;
//    }
//
//    private String decryptData(String data) throws Exception {
//        String[] parts = data.split(":");
//        String encryptedAESKey = parts[0];
//        String encryptedData = parts[1];
//        String aesKey = EncryptionUtil.decryptRSA(encryptedAESKey, rsaKeyConfig.getPrivateKey());
//        return EncryptionUtil.decryptAES(encryptedData, aesKey);
//    }
//}

//package com.fdu.capstone.service;
//
//import com.fdu.capstone.config.RSAKeyConfig;
//import com.fdu.capstone.model.User;
//import com.fdu.capstone.repository.UserRepository;
//import com.fdu.capstone.util.EncryptionUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private RSAKeyConfig rsaKeyConfig;
//
//    public User createUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRegistrationDate(LocalDateTime.now());
//
//        try {
//            String encryptedEmail = encryptData(user.getEmail());
//            logger.info("Encrypted email length: {}", encryptedEmail.length());
//            user.setEmail(encryptedEmail);
//
//            String encryptedUsername = encryptData(user.getUsername());
//            logger.info("Encrypted username length: {}", encryptedUsername.length());
//            user.setUsername(encryptedUsername);
//
//            if (user.getPhoneNumber() != null) {
//                String encryptedPhoneNumber = encryptData(user.getPhoneNumber());
//                logger.info("Encrypted phone number length: {}", encryptedPhoneNumber.length());
//                user.setPhoneNumber(encryptedPhoneNumber);
//            }
//        } catch (Exception e) {
//            logger.error("Error encrypting user data", e);
//        }
//
//        return userRepository.save(user);
//    }
//
//    public User findByEmail(String email) {
//        try {
//            String encryptedEmail = encryptData(email);
//            User user = userRepository.findByEmail(encryptedEmail);
//            if (user != null) {
//                user.setEmail(decryptData(user.getEmail()));
//                user.setUsername(decryptData(user.getUsername()));
//                if (user.getPhoneNumber() != null) {
//                    user.setPhoneNumber(decryptData(user.getPhoneNumber()));
//                }
//            }
//            return user;
//        } catch (Exception e) {
//            logger.error("Error finding user by email", e);
//            return null;
//        }
//    }
//
//    public User getUserById(Long id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            try {
//                user.setEmail(decryptData(user.getEmail()));
//                user.setUsername(decryptData(user.getUsername()));
//                if (user.getPhoneNumber() != null) {
//                    user.setPhoneNumber(decryptData(user.getPhoneNumber()));
//                }
//            } catch (Exception e) {
//                logger.error("Error decrypting user data", e);
//            }
//            return user;
//        }
//        return null;
//    }
//
//    public List<User> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            try {
//                user.setEmail(decryptData(user.getEmail()));
//                user.setUsername(decryptData(user.getUsername()));
//                if (user.getPhoneNumber() != null) {
//                    user.setPhoneNumber(decryptData(user.getPhoneNumber()));
//                }
//            } catch (Exception e) {
//                logger.error("Error decrypting user data", e);
//            }
//        }
//        return users;
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    private String encryptData(String data) throws Exception {
//        String aesKey = EncryptionUtil.generateAESKey();
//        String encryptedData = EncryptionUtil.encryptAES(data, aesKey);
//        String encryptedAESKey = EncryptionUtil.encryptRSA(aesKey, rsaKeyConfig.getPublicKey());
//        return encryptedAESKey + ":" + encryptedData;
//    }
//
//    private String decryptData(String data) throws Exception {
//        String[] parts = data.split(":");
//        String encryptedAESKey = parts[0];
//        String encryptedData = parts[1];
//        String aesKey = EncryptionUtil.decryptRSA(encryptedAESKey, rsaKeyConfig.getPrivateKey());
//        return EncryptionUtil.decryptAES(encryptedData, aesKey);
//    }
//
//    public User authenticateUser(String email, String password) {
//        User user = findByEmail(email);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return user;
//        } else {
//            logger.error("Invalid credentials for user: " + email);
//            return null;
//        }
//    }
//}

package com.fdu.capstone.service;

import com.fdu.capstone.config.RSAKeyConfig;
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

    @Autowired
    private RSAKeyConfig rsaKeyConfig;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());

        try {
            user.setPhoneNumber(encryptData(user.getPhoneNumber()));
            user.setUsername(encryptData(user.getUsername()));
        } catch (Exception e) {
            logger.error("Error encrypting user data", e);
        }

        return userRepository.save(user);
    }

    public User findByPhoneNumber(String phoneNumber) {
        try {
            String encryptedPhoneNumber = encryptData(phoneNumber);
            User user = userRepository.findByPhoneNumber(encryptedPhoneNumber);
            if (user != null) {
                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
                user.setUsername(decryptData(user.getUsername()));
            }
            return user;
        } catch (Exception e) {
            logger.error("Error finding user by phone number", e);
            return null;
        }
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            try {
                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
                user.setUsername(decryptData(user.getUsername()));
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
                user.setPhoneNumber(decryptData(user.getPhoneNumber()));
                user.setUsername(decryptData(user.getUsername()));
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
        String aesKey = EncryptionUtil.generateAESKey();
        String compressedData = EncryptionUtil.compress(data);
        String encryptedData = EncryptionUtil.encryptAES(compressedData, aesKey);
        String encryptedAESKey = EncryptionUtil.encryptRSA(aesKey, rsaKeyConfig.getPublicKey());
        return encryptedAESKey + ":" + encryptedData;
    }

    private String decryptData(String data) throws Exception {
        String[] parts = data.split(":");
        String encryptedAESKey = parts[0];
        String encryptedData = parts[1];
        String aesKey = EncryptionUtil.decryptRSA(encryptedAESKey, rsaKeyConfig.getPrivateKey());
        String decryptedData = EncryptionUtil.decryptAES(encryptedData, aesKey);
        return EncryptionUtil.decompress(decryptedData);
    }

    public User authenticateUser(String phoneNumber, String password) throws Exception {
        try {
            String encryptedPhoneNumber = encryptData(phoneNumber);
            User user = userRepository.findByPhoneNumber(encryptedPhoneNumber);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                user.setPhoneNumber(phoneNumber); // Set the decrypted phone number
                user.setUsername(decryptData(user.getUsername())); // Decrypt the username
                return user;
            } else {
                throw new Exception("Invalid credentials");
            }
        } catch (Exception e) {
            logger.error("Error during authentication: ", e);
            throw new Exception("Authentication failed");
        }
    }
}














