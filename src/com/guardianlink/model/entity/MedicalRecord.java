package com.guardianlink.model.entity;

/**
 * Medical Record entity for tracking children's health information
 */
public class MedicalRecord {
    private String recordId;
    private String childId;
    private String date;
    private String diagnosis;
    private String treatment;
    private String doctorName;
    private String notes;
    
    public MedicalRecord(String recordId, String childId, String date, String diagnosis, String treatment, String doctorName) {
        this.recordId = recordId;
        this.childId = childId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctorName = doctorName;
        this.notes = "";
    }
    
    // Getters and Setters
    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }
    
    public String getChildId() { return childId; }
    public void setChildId(String childId) { this.childId = childId; }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
