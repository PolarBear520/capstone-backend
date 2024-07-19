package com.fdu.capstone.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
        // 设置足够长的密钥
        jwtUtil.setSecret("a-very-long-secret-key-that-is-at-least-32-characters-long");
        jwtUtil.setExpirationTime(1000 * 60 * 60); // 设置 1 小时的过期时间
    }

    @Test
    public void testGenerateToken() {
        UserDetails userDetails = User.withUsername("testuser").password("password").authorities("USER").build();
        String token = jwtUtil.generateToken(userDetails);
        assertTrue(token != null && !token.isEmpty());
    }

    @Test
    public void testValidateToken() {
        UserDetails userDetails = User.withUsername("testuser").password("password").authorities("USER").build();
        String token = jwtUtil.generateToken(userDetails);
        assertTrue(jwtUtil.validateToken(token, userDetails));
        assertEquals("testuser", jwtUtil.extractUsername(token));
    }
}
