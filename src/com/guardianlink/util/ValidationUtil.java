package com.guardianlink.util;

/**
 * Utility class for input validation
 */
public class ValidationUtil {
    
    /**
     * Validates email format
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
    
    /**
     * Validates phone number format
     */
    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        // Simple validation for demo
        return phone.length() >= 10 && phone.matches("[0-9+\\-\\s]+");
    }
    
    /**
     * Validates if string is not null or empty
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
    
    /**
     * Validates age range
     */
    public static boolean isValidAge(int age) {
        return age > 0 && age < 150;
    }
}
