package com.example.passwordmanager;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class Crypto {
    private static final int KEY_SIZE = 256;
    private static final int IV_SIZE = 12;
    private static final int TAG_SIZE = 128;
    private static SecretKey key;
    private static final SecureRandom RNG = new SecureRandom();

    // private constructor
    private Crypto(){}

    // initializing the key
    public static void init (SecretKey sessionKey){
        key = sessionKey;
    }

    // method for generating a secret key
    public static SecretKey generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(KEY_SIZE);
            return keyGen.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Error generating key", e);
        }
    }

    // a method for the encryption of the plain text
    public static String encrypt (String plainText){
        if (plainText == null) return null;
        try {
            // generating random bytes for the base(IV)
            byte[] iv = new byte[IV_SIZE];
            RNG.nextBytes(iv);

            // initializing a cipher
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_SIZE, iv));

            // encrypting the bytes
            byte[] ct = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            // storing the IV and cipher text together for easier later decryption
            byte[] iv_ct = new byte[iv.length + ct.length];
            System.arraycopy(iv, 0, iv_ct, 0, iv.length);
            System.arraycopy(ct, 0, iv_ct, iv.length, ct.length);

            // returning encrypted bits as a string converted to Base 64 for easier storage in the DB
            return Base64.getEncoder().encodeToString(iv_ct);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
