package com.guardianlink.model.user;

/**
 * Enum representing different user roles in the GuardianLink system
 */
public enum UserRole {
    SYSTEM_ADMIN("System Administrator"),
    ORGANIZATION_ADMIN("Organization Administrator"),
    CAREGIVER("Caregiver"),
    DONOR("Donor"),
    AUDITOR("Auditor");
    
    private final String displayName;
    
    UserRole(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
