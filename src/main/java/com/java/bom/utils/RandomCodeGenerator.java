package com.java.bom.utils;

import java.security.SecureRandom;
import java.util.UUID;

public class RandomCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    private RandomCodeGenerator() {}

    public static String generateUUIDCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
    }

    public static String generateSecureRandomCode() {
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    // Test Metodu
    public static void main(String[] args) {
        System.out.println("UUID ile Üretilen Kod: " + generateUUIDCode());
        System.out.println("SecureRandom ile Üretilen Kod: " + generateSecureRandomCode());
    }
}