////package com.fdu.capstone.util;
////
////import javax.crypto.Cipher;
////import javax.crypto.KeyGenerator;
////import javax.crypto.SecretKey;
////import java.util.Base64;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////
////public class EncryptionUtil {
////    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);
////    private static SecretKey key;
////    private static Cipher cipher;
////
////    static {
////        try {
////            key = KeyGenerator.getInstance("AES").generateKey();
////            cipher = Cipher.getInstance("AES");
////        } catch (Exception e) {
////            logger.error("Error initializing EncryptionUtil", e);
////        }
////    }
////
////    public static String encrypt(String data) throws Exception {
////        cipher.init(Cipher.ENCRYPT_MODE, key);
////        byte[] encryptedData = cipher.doFinal(data.getBytes());
////        return Base64.getEncoder().encodeToString(encryptedData);
////    }
////
////    public static String decrypt(String encryptedData) throws Exception {
////        cipher.init(Cipher.DECRYPT_MODE, key);
////        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
////        return new String(cipher.doFinal(decodedData));
////    }
////}
//
////package com.fdu.capstone.util;
////
////import javax.crypto.Cipher;
////import javax.crypto.KeyGenerator;
////import javax.crypto.SecretKey;
////import javax.crypto.spec.SecretKeySpec;
////import java.security.KeyFactory;
////import java.security.PrivateKey;
////import java.security.PublicKey;
////import java.security.spec.PKCS8EncodedKeySpec;
////import java.security.spec.X509EncodedKeySpec;
////import java.util.Base64;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////
////public class EncryptionUtil {
////    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);
////    private static final String RSA_ALGORITHM = "RSA";
////    private static final String AES_ALGORITHM = "AES";
////
////    public static String generateAESKey() throws Exception {
////        KeyGenerator keyGen = KeyGenerator.getInstance(AES_ALGORITHM);
////        keyGen.init(256); // for AES-256
////        SecretKey secretKey = keyGen.generateKey();
////        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
////    }
////
////    public static String encryptAES(String data, String aesKey) throws Exception {
////        SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(aesKey), AES_ALGORITHM);
////        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
////        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
////        byte[] encryptedData = cipher.doFinal(data.getBytes());
////        return Base64.getEncoder().encodeToString(encryptedData);
////    }
////
////    public static String decryptAES(String encryptedData, String aesKey) throws Exception {
////        SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(aesKey), AES_ALGORITHM);
////        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
////        cipher.init(Cipher.DECRYPT_MODE, keySpec);
////        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
////        return new String(cipher.doFinal(decodedData));
////    }
////
////    public static String encryptRSA(String data, PublicKey publicKey) throws Exception {
////        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
////        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
////        byte[] encryptedData = cipher.doFinal(data.getBytes());
////        return Base64.getEncoder().encodeToString(encryptedData);
////    }
////
////    public static String decryptRSA(String encryptedData, PrivateKey privateKey) throws Exception {
////        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
////        cipher.init(Cipher.DECRYPT_MODE, privateKey);
////        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
////        return new String(cipher.doFinal(decodedData));
////    }
////
////    public static PublicKey getPublicKey(String base64PublicKey) throws Exception {
////        byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
////        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
////        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
////        return keyFactory.generatePublic(spec);
////    }
////
////    public static PrivateKey getPrivateKey(String base64PrivateKey) throws Exception {
////        byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);
////        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
////        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
////        return keyFactory.generatePrivate(spec);
////    }
////}
////
//
////package com.fdu.capstone.util;
////
////import javax.crypto.Cipher;
////import javax.crypto.KeyGenerator;
////import javax.crypto.SecretKey;
////import javax.crypto.spec.SecretKeySpec;
////import java.security.KeyFactory;
////import java.security.PrivateKey;
////import java.security.PublicKey;
////import java.security.spec.PKCS8EncodedKeySpec;
////import java.security.spec.X509EncodedKeySpec;
////import java.util.Base64;
////
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////
////public class EncryptionUtil {
////    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);
////    private static Cipher aesCipher;
////    private static Cipher rsaCipher;
////    private static PublicKey publicKey;
////    private static PrivateKey privateKey;
////
////    static {
////        try {
////            aesCipher = Cipher.getInstance("AES");
////            rsaCipher = Cipher.getInstance("RSA");
////        } catch (Exception e) {
////            logger.error("Error initializing EncryptionUtil", e);
////        }
////    }
////
////    public static void setPublicKey(String key) throws Exception {
////        byte[] byteKey = Base64.getDecoder().decode(key.getBytes());
////        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
////        KeyFactory kf = KeyFactory.getInstance("RSA");
////        publicKey = kf.generatePublic(X509publicKey);
////    }
////
////    public static void setPrivateKey(String key) throws Exception {
////        byte[] byteKey = Base64.getDecoder().decode(key.getBytes());
////        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(byteKey);
////        KeyFactory kf = KeyFactory.getInstance("RSA");
////        privateKey = kf.generatePrivate(spec);
////    }
////
////    public static String generateAESKey() throws Exception {
////        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
////        keyGen.init(128); // for example
////        SecretKey secretKey = keyGen.generateKey();
////        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
////    }
////
////    public static String encryptAES(String data, String key) throws Exception {
////        if (data == null) {
////            throw new IllegalArgumentException("Data to encrypt cannot be null");
////        }
////        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
////        aesCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
////        byte[] encryptedData = aesCipher.doFinal(data.getBytes());
////        return Base64.getEncoder().encodeToString(encryptedData);
////    }
////
////    public static String decryptAES(String data, String key) throws Exception {
////        if (data == null) {
////            throw new IllegalArgumentException("Data to decrypt cannot be null");
////        }
////        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
////        aesCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
////        byte[] decodedData = Base64.getDecoder().decode(data);
////        return new String(aesCipher.doFinal(decodedData));
////    }
////
////    public static String encryptRSA(String data, PublicKey publicKey) throws Exception {
////        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
////        byte[] encryptedData = rsaCipher.doFinal(data.getBytes());
////        return Base64.getEncoder().encodeToString(encryptedData);
////    }
////
////    public static String decryptRSA(String data, PrivateKey privateKey) throws Exception {
////        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
////        byte[] decodedData = Base64.getDecoder().decode(data);
////        return new String(rsaCipher.doFinal(decodedData));
////    }
////}
//
//package com.fdu.capstone.util;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.KeyFactory;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//import java.util.zip.Deflater;
//import java.util.zip.Inflater;
//
//public class EncryptionUtil {
//
//    public static String generateAESKey() throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(128); // for example
//        SecretKey secretKey = keyGen.generateKey();
//        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
//    }
//
//    public static String encryptAES(String data, String aesKey) throws Exception {
//        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
//    }
//
//    public static String decryptAES(String encryptedData, String aesKey) throws Exception {
//        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)), "UTF-8");
//    }
//
//    public static String encryptRSA(String data, PublicKey publicKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
//    }
//
//    public static String decryptRSA(String encryptedData, PrivateKey privateKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)), "UTF-8");
//    }
//
//    public static String compress(String data) throws Exception {
//        byte[] input = data.getBytes("UTF-8");
//        Deflater deflater = new Deflater();
//        deflater.setInput(input);
//        deflater.finish();
//        byte[] buffer = new byte[1024];
//        int compressedDataLength = deflater.deflate(buffer);
//        byte[] compressedData = new byte[compressedDataLength];
//        System.arraycopy(buffer, 0, compressedData, 0, compressedDataLength);
//        return Base64.getEncoder().encodeToString(compressedData);
//    }
//
//    public static String decompress(String compressedData) throws Exception {
//        byte[] output = Base64.getDecoder().decode(compressedData);
//        Inflater inflater = new Inflater();
//        inflater.setInput(output, 0, output.length);
//        byte[] buffer = new byte[1024];
//        int resultLength = inflater.inflate(buffer);
//        inflater.end();
//        return new String(buffer, 0, resultLength, "UTF-8");
//    }
//}
//
//
//
//
//
