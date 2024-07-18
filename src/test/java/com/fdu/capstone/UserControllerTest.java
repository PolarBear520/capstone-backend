//package com.fdu.capstone;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fdu.capstone.controller.UserController;
//import com.fdu.capstone.model.User;
//import com.fdu.capstone.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//@ActiveProfiles("test")
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    private User user;
//
//    @BeforeEach
//    public void setUp() {
//        user = new User();
//        user.setUserId(1L);
//        user.setUsername("testuser");
//        user.setPassword("password");
//        user.setEmail("test@example.com");
//        user.setPhoneNumber("1234567890");
//        user.setRegistrationDate(LocalDateTime.now());
//    }
//
//    @Test
//    public void testCreateUser() throws Exception {
//        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String userJson = objectMapper.writeValueAsString(user);
//
//        mockMvc.perform(post("/api/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userId").value(user.getUserId()))
//                .andExpect(jsonPath("$.username").value(user.getUsername()))
//                .andExpect(jsonPath("$.email").value(user.getEmail()))
//                .andExpect(jsonPath("$.phoneNumber").value(user.getPhoneNumber()));
//    }
//
//    @Test
//    public void testGetUser() throws Exception {
//        Mockito.when(userService.getUserById(1L)).thenReturn(user);
//
//        mockMvc.perform(get("/api/users/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userId").value(user.getUserId()))
//                .andExpect(jsonPath("$.username").value(user.getUsername()))
//                .andExpect(jsonPath("$.email").value(user.getEmail()))
//                .andExpect(jsonPath("$.phoneNumber").value(user.getPhoneNumber()));
//    }
//
//    @Test
//    public void testGetAllUsers() throws Exception {
//        List<User> users = Collections.singletonList(user);
//        Mockito.when(userService.getAllUsers()).thenReturn(users);
//
//        mockMvc.perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].userId").value(user.getUserId()))
//                .andExpect(jsonPath("$[0].username").value(user.getUsername()))
//                .andExpect(jsonPath("$[0].email").value(user.getEmail()))
//                .andExpect(jsonPath("$[0].phoneNumber").value(user.getPhoneNumber()));
//    }
//
//    @Test
//    public void testDeleteUser() throws Exception {
//        mockMvc.perform(delete("/api/users/1"))
//                .andExpect(status().isOk());
//    }
//}
package com.fdu.capstone;

import com.fdu.capstone.controller.UserController;
import com.fdu.capstone.model.User;
import com.fdu.capstone.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
//        user.setEmail("testuser@example.com");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"password\", \"email\": \"testuser@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }
}
