//package com.fdu.capstone.util;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.zip.GZIPInputStream;
//import java.util.zip.GZIPOutputStream;
//
//public class CompressionUtil {
//
//    // 压缩数据
//    public static byte[] compress(String data) throws IOException {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
//            gzipOutputStream.write(data.getBytes());
//        }
//        return byteArrayOutputStream.toByteArray();
//    }
//
//    // 解压缩数据
//    public static String decompress(byte[] compressedData) throws IOException {
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedData);
//        try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
//             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
//            byte[] buffer = new byte[256];
//            int len;
//            while ((len = gzipInputStream.read(buffer)) > 0) {
//                byteArrayOutputStream.write(buffer, 0, len);
//            }
//            return byteArrayOutputStream.toString();
//        }
//    }
//}
