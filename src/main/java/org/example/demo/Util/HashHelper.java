package org.example.demo.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashHelper {

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-512");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return Base64.getEncoder().encodeToString(digest); //  returns a string version of the hash we can save in the database
    }
}
