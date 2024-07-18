package com.fdu.capstone.controller;

import com.fdu.capstone.config.RSAKeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class KeyController {

    @Autowired
    private RSAKeyConfig rsaKeyConfig;

    @GetMapping("/publicKey")
    public String getPublicKey() {
        PublicKey publicKey = rsaKeyConfig.getPublicKey();
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}
