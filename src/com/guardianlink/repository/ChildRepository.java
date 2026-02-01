package com.guardianlink.repository;

import com.guardianlink.model.entity.Child;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository for managing Child data (in-memory storage)
 */
public class ChildRepository {
    private static ChildRepository instance;
    private List<Child> children;
    
    private ChildRepository() {
        children = new ArrayList<>();
        initializeSampleData();
    }
    
    public static ChildRepository getInstance() {
        if (instance == null) {
            instance = new ChildRepository();
        }
        return instance;
    }

     /// Initialize with sample children for testing
    private void initializeSampleData() {
        children.add(new Child("C001", "Rahim Ahmed", 8, "Male", "ORG001", "2024-01-15"));
        children.add(new Child("C002", "Fatima Rahman", 10, "Female", "ORG001", "2024-02-20"));
        children.add(new Child("C003", "Karim Hassan", 7, "Male", "ORG002", "2024-03-10"));
        children.add(new Child("C004", "Ayesha Begum", 12, "Female", "ORG001", "2024-01-25"));
        children.add(new Child("C005", "Ibrahim Ali", 9, "Male", "ORG002", "2024-04-05"));
        children.add(new Child("C006", "Nadia Islam", 11, "Female", "ORG001", "2024-02-15"));
        children.add(new Child("C007", "Sohel Khan", 6, "Male", "ORG003", "2024-05-10"));
        children.add(new Child("C008", "Riya Sultana", 13, "Female", "ORG002", "2024-03-20"));
        
        // Set some as sponsored
        children.get(0).setSponsorshipStatus("Sponsored");
        children.get(1).setSponsorshipStatus("Sponsored");
        children.get(3).setSponsorshipStatus("Sponsored");
        children.get(5).setSponsorshipStatus("Sponsored");
    }
    
    /**
     * Find all children
     */
    public List<Child> findAll() {
        return new ArrayList<>(children);
    }
    
    /**
     * Find child by ID
     */
    public Optional<Child> findById(String childId) {
        return children.stream()
                .filter(c -> c.getChildId().equals(childId))
                .findFirst();
    }
    
    /**
     * Find children by organization
     */
    public List<Child> findByOrganization(String organizationId) {
        return children.stream()
                .filter(c -> c.getOrganizationId().equals(organizationId))
                .collect(Collectors.toList());
    }
    
    /**
     * Find children by sponsorship status
     */
    public List<Child> findBySponsorshipStatus(String status) {
        return children.stream()
                .filter(c -> c.getSponsorshipStatus().equals(status))
                .collect(Collectors.toList());
    }
    
    /**
     * Add new child
     */
    public void save(Child child) {
        children.add(child);
    }
    
    /**
     * Update existing child
     */
    public boolean update(Child child) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getChildId().equals(child.getChildId())) {
                children.set(i, child);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Delete child
     */
    public boolean delete(String childId) {
        return children.removeIf(c -> c.getChildId().equals(childId));
    }
    
    /**
     * Generate next child ID
     */
    public String generateNextId() {
        int maxId = children.stream()
                .map(Child::getChildId)
                .map(id -> Integer.parseInt(id.substring(1)))
                .max(Integer::compareTo)
                .orElse(0);
        return String.format("C%03d", maxId + 1);
    }
    
    /**
     * Get total count
     */
    public int count() {
        return children.size();
    }
}
