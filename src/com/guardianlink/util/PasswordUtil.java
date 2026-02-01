package com.guardianlink.util;

/**
 * Utility class for password operations
 * In a real application, this would use proper hashing (BCrypt, etc.)
 */
public class PasswordUtil {
    
    /**
     * Validates password strength
     * @param password Password to validate
     * @return true if password meets requirements
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 4) {
            return false;
        }
        return true;
    }
    
    /**
     * Simple password hashing (for demo purposes only)
     * In production, use BCrypt or similar
     */
    public static String hashPassword(String password) {
        // For demo, just return the password as-is
        // In production: return BCrypt.hashpw(password, BCrypt.gensalt());
        return password;
    }
    
    /**
     * Verify password against hash
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        // For demo, simple string comparison
        // In production: return BCrypt.checkpw(password, hashedPassword);
        return password.equals(hashedPassword);
    }
}
