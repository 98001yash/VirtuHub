package com.company.VirtuHub.User_service.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {


    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword){
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
