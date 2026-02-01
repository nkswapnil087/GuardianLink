package com.guardianlink.controller;

import com.guardianlink.model.entity.Child;
import com.guardianlink.model.user.Caregiver;
import com.guardianlink.service.ChildService;

import java.util.List;

/**
 * Controller for Caregiver-specific operations
 */
public class CaregiverController {
    private static CaregiverController instance;
    private ChildService childService;
    
    private CaregiverController() {
        childService = ChildService.getInstance();
    }
    
    public static CaregiverController getInstance() {
        if (instance == null) {
            instance = new CaregiverController();
        }
        return instance;
    }
    
    /**
     * Get children assigned to caregiver's organization
     */
    public List<Child> getAssignedChildren(Caregiver caregiver) {
        return childService.getChildrenByOrganization(caregiver.getOrganizationId());
    }
    
    /**
     * Update child information
     */
    public boolean updateChildInfo(Child child) {
        return childService.updateChild(child);
    }
}
