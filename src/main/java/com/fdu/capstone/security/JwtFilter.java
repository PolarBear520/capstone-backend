package com.fdu.capstone.security;

import com.fdu.capstone.model.User;
import com.fdu.capstone.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 跳过特定路径的验证
//        String path = request.getRequestURI();
//        if (path.startsWith("/api/orders")) {
//            chain.doFilter(request, response);
//            return;
//        }

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
                Long userId = jwtUtil.extractUserId(jwt);  // 提取用户ID
                logger.debug("Username extracted from JWT: {}", username);
                logger.debug("UserId extracted from JWT: {}", userId);
            } catch (Exception e) {
                logger.error("JWT token extraction failed", e);
            }
        } else {
            logger.warn("Authorization header is missing or doesn't start with Bearer");
        }

        logger.debug("JWT Token: {}", jwt);
        logger.debug("Username from token: {}", username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            try {
                userDetails = this.userService.loadUserByUsername(username);
                logger.debug("UserDetails loaded for username: {}", username);
            } catch (Exception e) {
                logger.error("UserDetailsService load failed", e);
            }

            if (userDetails != null && jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                logger.debug("User authenticated: {}", username);
            }
        }

        chain.doFilter(request, response);
    }
}

