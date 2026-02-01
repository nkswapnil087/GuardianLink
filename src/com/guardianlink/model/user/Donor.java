package com.guardianlink.model.user;

/**
 * Donor - Makes donations to support children
 */
public class Donor extends User {
    private double totalDonated;
    private String preferredPaymentMethod;
    
    public Donor(String userId, String username, String password, String fullName, String email) {
        super(userId, username, password, fullName, email, UserRole.DONOR);
        this.totalDonated = 0.0;
        this.preferredPaymentMethod = "Bank Transfer";
    }
    
    public double getTotalDonated() { return totalDonated; }
    public void setTotalDonated(double totalDonated) { this.totalDonated = totalDonated; }
    
    public String getPreferredPaymentMethod() { return preferredPaymentMethod; }
    public void setPreferredPaymentMethod(String preferredPaymentMethod) { 
        this.preferredPaymentMethod = preferredPaymentMethod; 
    }
    
    @Override
    public String getDashboardTitle() {
        return "Donor Dashboard";
    }
}
