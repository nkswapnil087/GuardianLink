package com.guardianlink.exception;

/**
 * Custom exception for insufficient balance scenarios
 */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
