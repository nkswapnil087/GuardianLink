package com.guardianlink.model.entity;

/**
 * Donation entity representing monetary contributions
 */
public class Donation {
    private String donationId;
    private String donorId;
    private String childId;
    private double amount;
    private String date;
    private String paymentMethod;
    private String status;
    
    public Donation(String donationId, String donorId, String childId, double amount, String date, String paymentMethod) {
        this.donationId = donationId;
        this.donorId = donorId;
        this.childId = childId;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.status = "Completed";
    }
    
    // Getters and Setters
    public String getDonationId() { return donationId; }
    public void setDonationId(String donationId) { this.donationId = donationId; }
    
    public String getDonorId() { return donorId; }
    public void setDonorId(String donorId) { this.donorId = donorId; }
    
    public String getChildId() { return childId; }
    public void setChildId(String childId) { this.childId = childId; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
