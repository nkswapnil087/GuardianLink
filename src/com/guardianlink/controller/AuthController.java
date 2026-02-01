package com.guardianlink.controller;

import com.guardianlink.model.user.User;
import com.guardianlink.service.UserService;
import com.guardianlink.exception.UserNotFoundException;

/**
 * Controller for authentication operations
 */
public class AuthController {
    private static AuthController instance;
    private UserService userService;
    private User currentUser;
    
    private AuthController() {
        userService = UserService.getInstance();
    }
    
    public static AuthController getInstance() {
        if (instance == null) {
            instance = new AuthController();
        }
        return instance;
    }
    
    /**
     * Authenticate user
     */
    public boolean login(String username, String password) {
        try {
            currentUser = userService.login(username, password);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }
    
    /**
     * Get currently logged in user
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Logout current user
     */
    public void logout() {
        currentUser = null;
    }
    
    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
