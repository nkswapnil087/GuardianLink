package com.guardianlink.repository;

import com.guardianlink.model.user.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing User data (in-memory storage)
 * In a real application, this would interact with a database
 */
public class UserRepository {
    private static UserRepository instance;
    private List<User> users;
    
    private UserRepository() {
        users = new ArrayList<>();
        initializeSampleData();
    }
    
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    
    /**
     * Initialize with sample users for testing
     */
    private void initializeSampleData() {
        // System Admin
        users.add(new SystemAdmin("U001", "admin", "admin123", "Admin User", "admin@guardianlink.com"));
        
        // Organization Admins
        users.add(new OrganizationAdmin("U002", "org1admin", "pass123", "Sarah Johnson", "sarah@hope.org", "ORG001"));
        
        // Caregivers
        users.add(new Caregiver("U003", "caregiver1", "pass123", "John Smith", "john@hope.org", "ORG001", "Child Psychology"));
        
        // Donors
        users.add(new Donor("U004", "donor1", "pass123", "Mr. Abdul Karim", "karim@email.com"));
        
        // Auditors
        users.add(new Auditor("U005", "auditor1", "pass123", "Emma Wilson", "emma@audit.com", "AUD-2024-001"));
    }
    
    /**
     * Find user by username and password
     */
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();
    }
    
    /**
     * Find user by ID
     */
    public Optional<User> findById(String userId) {
        return users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();
    }
    
    /**
     * Find user by username
     */
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
    
    /**
     * Get all users
     */
    public List<User> findAll() {
        return new ArrayList<>(users);
    }
    
    /**
     * Add new user
     */
    public void save(User user) {
        users.add(user);
    }
    
    /**
     * Delete user
     */
    public boolean delete(String userId) {
        return users.removeIf(u -> u.getUserId().equals(userId));
    }
    
    /**
     * Generate next user ID
     */
    public String generateNextId() {
        int maxId = users.stream()
                .map(User::getUserId)
                .map(id -> Integer.parseInt(id.substring(1)))
                .max(Integer::compareTo)
                .orElse(0);
        return String.format("U%03d", maxId + 1);
    }
}
