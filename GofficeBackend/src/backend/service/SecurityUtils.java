package backend.service;

import org.mindrot.jbcrypt.BCrypt;

public class SecurityUtils {

    // Hash a password using bcrypt
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}

