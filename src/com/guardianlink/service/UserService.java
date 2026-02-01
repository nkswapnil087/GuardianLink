package com.guardianlink.service;

import com.guardianlink.model.user.User;
import com.guardianlink.repository.UserRepository;
import com.guardianlink.exception.UserNotFoundException;
import com.guardianlink.util.PasswordUtil;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for User-related business logic
 */
public class UserService {
    private static UserService instance;
    private UserRepository userRepository;
    
    private UserService() {
        userRepository = UserRepository.getInstance();
    }
    
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    /**
     * Authenticate user with username and password
     */
    public User login(String username, String password) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent() && user.get().isActive()) {
            return user.get();
        }
        throw new UserNotFoundException("Invalid username or password");
    }
    
    /**
     * Register new user
     */
    public User registerUser(User user) {
        // In a real app, would validate and hash password
        userRepository.save(user);
        return user;
    }
    
    /**
     * Get user by ID
     */
    public User getUserById(String userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }
    
    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Delete user
     */
    public boolean deleteUser(String userId) {
        return userRepository.delete(userId);
    }
    
    /**
     * Generate next user ID
     */
    public String generateNextUserId() {
        return userRepository.generateNextId();
    }
}
