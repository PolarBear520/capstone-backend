package com.fdu.capstone.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);
    private static SecretKey key;
    private static Cipher cipher;

    static {
        try {
            key = KeyGenerator.getInstance("AES").generateKey();
            cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            logger.error("Error initializing EncryptionUtil", e);
        }
    }

    public static String encrypt(String data) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decodedData));
    }
}
