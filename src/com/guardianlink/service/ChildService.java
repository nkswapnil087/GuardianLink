package com.guardianlink.service;

import com.guardianlink.model.entity.Child;
import com.guardianlink.repository.ChildRepository;

import java.util.List;

/**
 * Service layer for Child-related business logic
 */
public class ChildService {
    private static ChildService instance;
    private ChildRepository childRepository;
    
    private ChildService() {
        childRepository = ChildRepository.getInstance();
    }
    
    public static ChildService getInstance() {
        if (instance == null) {
            instance = new ChildService();
        }
        return instance;
    }
    
    /**
     * Get all children
     */
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }
    
    /**
     * Get child by ID
     */
    public Child getChildById(String childId) {
        return childRepository.findById(childId).orElse(null);
    }
    
    /**
     * Get children by organization
     */
    public List<Child> getChildrenByOrganization(String organizationId) {
        return childRepository.findByOrganization(organizationId);
    }
    
    /**
     * Add new child
     */
    public Child addChild(Child child) {
        childRepository.save(child);
        return child;
    }
    
    /**
     * Update child information
     */
    public boolean updateChild(Child child) {
        return childRepository.update(child);
    }
    
    /**
     * Delete child
     */
    public boolean deleteChild(String childId) {
        return childRepository.delete(childId);
    }
    
    /**
     * Get total children count
     */
    public int getTotalChildrenCount() {
        return childRepository.count();
    }
    
    /**
     * Get sponsored children count
     */
    public int getSponsoredChildrenCount() {
        return (int) childRepository.findBySponsorshipStatus("Sponsored").size();
    }
    
    /**
     * Generate next child ID
     */
    public String generateNextChildId() {
        return childRepository.generateNextId();
    }
}
