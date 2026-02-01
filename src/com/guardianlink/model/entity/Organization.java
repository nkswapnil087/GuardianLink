package com.guardianlink.model.entity;


// Organization entity representing NGOs in the system
public class Organization {
    private String organizationId;
    private String name;
    private String address;
    private String contactNumber;
    private String email;
    private int totalChildren;
    
    public Organization(String organizationId, String name, String address, String contactNumber, String email) {
        this.organizationId = organizationId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.totalChildren = 0;
    }
    
    // Getters and Setters
    public String getOrganizationId() { return organizationId; }
    public void setOrganizationId(String organizationId) { this.organizationId = organizationId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public int getTotalChildren() { return totalChildren; }
    public void setTotalChildren(int totalChildren) { this.totalChildren = totalChildren; }
    
    @Override
    public String toString() {
        return name;
    }
}
