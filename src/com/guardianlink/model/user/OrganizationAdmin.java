package com.guardianlink.model.user;

/**
 * Organization Administrator
 * Can manage children and caregivers within their organization
 */
public class OrganizationAdmin extends User {
    private String organizationId;
    
    public OrganizationAdmin(String userId, String username, String password, String fullName, String email, String organizationId) {
        super(userId, username, password, fullName, email, UserRole.ORGANIZATION_ADMIN);
        this.organizationId = organizationId;
    }
    
    public String getOrganizationId() { return organizationId; }
    public void setOrganizationId(String organizationId) { this.organizationId = organizationId; }
    
    @Override
    public String getDashboardTitle() {
        return "Organization Administrator Dashboard";
    }
}
