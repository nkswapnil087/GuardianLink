package com.guardianlink.exception;

/**
 * Custom exception for when a user is not found in the system
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
