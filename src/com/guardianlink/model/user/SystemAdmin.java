package com.guardianlink.model.user;

/**
 * System Administrator - Highest level of access
 * Can manage all aspects of the system
 */
public class SystemAdmin extends User {
    
    public SystemAdmin(String userId, String username, String password, String fullName, String email) {
        super(userId, username, password, fullName, email, UserRole.SYSTEM_ADMIN);
    }
    
    @Override
    public String getDashboardTitle() {
        return "System Administrator Dashboard";
    }
}
