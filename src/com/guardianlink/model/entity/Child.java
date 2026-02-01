package com.guardianlink.model.entity;


/// Child entity representing children in the welfare system
public class Child {
    private String childId;
    private String name;
    private int age;
    private String gender;
    private String organizationId;
    private String sponsorshipStatus;
    private String dateAdmitted;
    private String emergencyContact;
    
    public Child(String childId, String name, int age, String gender, String organizationId, String dateAdmitted) {
        this.childId = childId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.organizationId = organizationId;
        this.sponsorshipStatus = "Awaiting Sponsor";
        this.dateAdmitted = dateAdmitted;
        this.emergencyContact = "N/A";
    }
    
    // Getters and Setters
    public String getChildId() { return childId; }
    public void setChildId(String childId) { this.childId = childId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getOrganizationId() { return organizationId; }
    public void setOrganizationId(String organizationId) { this.organizationId = organizationId; }
    
    public String getSponsorshipStatus() { return sponsorshipStatus; }
    public void setSponsorshipStatus(String sponsorshipStatus) { this.sponsorshipStatus = sponsorshipStatus; }
    
    public String getDateAdmitted() { return dateAdmitted; }
    public void setDateAdmitted(String dateAdmitted) { this.dateAdmitted = dateAdmitted; }
    
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    
    @Override
    public String toString() {
        return name + " (Age: " + age + ")";
    }
}
