package com.guardianlink.model.user;

/**
 * Caregiver - Takes care of children in organizations
 */
public class Caregiver extends User {
    private String organizationId;
    private String specialization;
    
    public Caregiver(String userId, String username, String password, String fullName, String email, String organizationId, String specialization) {
        super(userId, username, password, fullName, email, UserRole.CAREGIVER);
        this.organizationId = organizationId;
        this.specialization = specialization;
    }
    
    public String getOrganizationId() { return organizationId; }
    public void setOrganizationId(String organizationId) { this.organizationId = organizationId; }
    
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    
    @Override
    public String getDashboardTitle() {
        return "Caregiver Dashboard";
    }
}
