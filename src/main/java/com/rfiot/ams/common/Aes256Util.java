package com.rfiot.ams.common;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* 사용법
byte[] key = "92c42d9dk49dhj3480gh280h08sb80f0".getBytes();
String plainText = "minamson손미남";

String encrypt = Aes256Util.encryptByFix(plainText, key);
String decyprt = Aes256Util.decrypt(encrypt, key);
*/

public class Aes256Util {
    public static final int GCM_IV_LENGTH = 16;
    public static final int GCM_TAG_LENGTH = 128;

    public static String encryptByRandom(String plaintext, byte[] key)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        if (plaintext == null) {
            return null;
        }
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[GCM_IV_LENGTH];
        random.nextBytes(iv);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        byte[] cipherText = cipher.doFinal(plaintext.getBytes());
        return new String(Base64.getEncoder().encode(concatenate(iv, cipherText)));
    }

    public static String encryptByFix(String plaintext, byte[] key)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        if (plaintext == null) {
            return null;
        }
        byte[] iv = Arrays.copyOfRange(key, 0, GCM_IV_LENGTH);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        byte[] cipherText = cipher.doFinal(plaintext.getBytes());
        return new String(Base64.getEncoder().encode(concatenate(iv, cipherText)));
    }

    public static String decrypt(String str, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        if (str == null) {
            return null;
        }
        byte[] ciphertext = Base64.getDecoder().decode(str.getBytes());
        byte[] iv = Arrays.copyOfRange(ciphertext, 0, GCM_IV_LENGTH);
        byte[] cipherText = Arrays.copyOfRange(ciphertext, GCM_IV_LENGTH, ciphertext.length);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
        return new String(cipher.doFinal(cipherText));
    }

    private static byte[] concatenate(byte[] firstArray, byte[] secondArray) {
        byte[] result = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }
}

