package com.fdu.capstone.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAKeyGenerator {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Create KeyPair generator and initialize it
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        // Generate KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Print the public and private keys
        System.out.println("Public Key:");
        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        System.out.println("Private Key:");
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
    }
}
