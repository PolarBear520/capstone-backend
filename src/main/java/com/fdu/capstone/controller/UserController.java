//    package com.fdu.capstone.controller;
//
//    import com.fdu.capstone.config.RSAKeyConfig;
//    import com.fdu.capstone.model.User;
//    import com.fdu.capstone.service.UserService;
//    import org.slf4j.Logger;
//    import org.slf4j.LoggerFactory;
//    import org.springframework.beans.factory.annotation.Autowired;
//    import org.springframework.http.HttpStatus;
//    import org.springframework.http.ResponseEntity;
//    import org.springframework.web.bind.annotation.*;
//
//    import java.util.List;
//
//    @RestController
//    @RequestMapping("/api/users")
//    public class UserController {
//
//        private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//        @Autowired
//        private UserService userService;
//
////        @Autowired
////        private RSAKeyConfig rsaKeyConfig;
//
//        @PostMapping("/register")
//        public ResponseEntity<User> registerUser(@RequestBody User user) {
//            try {
//                User createdUser = userService.createUser(user);
//                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//            } catch (Exception e) {
//                logger.error("Error registering user", e);
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<User> getUser(@PathVariable Long id) {
//            User user = userService.getUserById(id);
//            return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        @GetMapping("/email/{email}")
//        public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
//            User user = userService.findByEmail(email);
//            return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        @GetMapping
//        public ResponseEntity<List<User>> getAllUsers() {
//            List<User> users = userService.getAllUsers();
//            return ResponseEntity.ok(users);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//            try {
//                userService.deleteUser(id);
//                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//            } catch (Exception e) {
//                logger.error("Error deleting user", e);
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        }
//    }
//

package com.fdu.capstone.controller;

import com.fdu.capstone.model.User;
import com.fdu.capstone.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            logger.error("Error registering user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        try {
            String phoneNumber = loginRequest.get("phoneNumber");
            String password = loginRequest.get("password");
            User user = userService.authenticateUser(phoneNumber, password);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("Error authenticating user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<User> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        User user = userService.findByPhoneNumber(phoneNumber);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}








