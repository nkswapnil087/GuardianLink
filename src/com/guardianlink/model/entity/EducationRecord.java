package com.guardianlink.model.entity;

/**
 * Education Record entity for tracking children's academic progress
 */
public class EducationRecord {
    private String recordId;
    private String childId;
    private String schoolName;
    private String grade;
    private double gpa;
    private String academicYear;
    private String teacherComment;
    
    public EducationRecord(String recordId, String childId, String schoolName, String grade, double gpa, String academicYear) {
        this.recordId = recordId;
        this.childId = childId;
        this.schoolName = schoolName;
        this.grade = grade;
        this.gpa = gpa;
        this.academicYear = academicYear;
        this.teacherComment = "";
    }
    
    // Getters and Setters
    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }
    
    public String getChildId() { return childId; }
    public void setChildId(String childId) { this.childId = childId; }
    
    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }
    
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    
    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }
    
    public String getTeacherComment() { return teacherComment; }
    public void setTeacherComment(String teacherComment) { this.teacherComment = teacherComment; }
}
