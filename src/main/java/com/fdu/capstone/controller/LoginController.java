//package com.fdu.capstone.controller;
//
//import com.fdu.capstone.config.RSAKeyConfig;
////import com.fdu.capstone.model.LoginRequest;
//import com.fdu.capstone.model.User;
//import com.fdu.capstone.service.UserService;
//import com.fdu.capstone.util.EncryptionUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/")
//public class LoginController {
//
//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private RSAKeyConfig rsaKeyConfig;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
//        try {
//            // 解密 AES 密钥
//            String decryptedAESKey = EncryptionUtil.decryptRSA(loginRequest.getAesKey(), rsaKeyConfig.getPrivateKey());
//
//            // 解密密码
//            String decryptedPassword = EncryptionUtil.decryptAES(loginRequest.getPassword(), decryptedAESKey);
//
//            // 查找用户
//            User user = userService.findByEmail(loginRequest.getEmail());
//
//            if (user == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//            }
//
//            // 验证密码
//            if (!passwordEncoder.matches(decryptedPassword, user.getPassword())) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//            }
//
//            // 登录成功
//            return ResponseEntity.ok("Login successful");
//        } catch (Exception e) {
//            logger.error("Login error", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
//        }
//    }
//}
