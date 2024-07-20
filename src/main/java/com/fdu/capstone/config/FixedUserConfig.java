package com.fdu.capstone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixedUserConfig {
    @Value("${fixed.user.id}")
    private Long fixedUserId;

    public Long getFixedUserId() {
        return fixedUserId;
    }
}
