package com.guardianlink.model.user;

/**
 * Abstract base class for all users in the GuardianLink system
 * Demonstrates OOP principles: Abstraction and Inheritance
 */
public abstract class User {
    private String userId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private UserRole role;
    private boolean isActive;
    
    public User(String userId, String username, String password, String fullName, String email, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.isActive = true;
    }
    
    // Abstract method to be implemented by subclasses
    public abstract String getDashboardTitle();
    
    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    @Override
    public String toString() {
        return fullName + " (" + role.getDisplayName() + ")";
    }
}
