package com.guardianlink.model.user;

//Auditor - Reviews and audits financial records and operations
public class Auditor extends User {
    private String certificationNumber;
    
    public Auditor(String userId, String username, String password, String fullName, String email, String certificationNumber) {
        super(userId, username, password, fullName, email, UserRole.AUDITOR);
        this.certificationNumber = certificationNumber;
    }
    
    public String getCertificationNumber() { return certificationNumber; }
    public void setCertificationNumber(String certificationNumber) { 
        this.certificationNumber = certificationNumber; 
    }
    
    @Override
    public String getDashboardTitle() {
        return "Auditor Dashboard";
    }
}
