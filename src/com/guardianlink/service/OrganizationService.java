package com.guardianlink.service;

import com.guardianlink.model.entity.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for Organization-related business logic
 */
public class OrganizationService {
    private static OrganizationService instance;
    private List<Organization> organizations;
    
    private OrganizationService() {
        organizations = new ArrayList<>();
        initializeSampleData();
    }
    
    public static OrganizationService getInstance() {
        if (instance == null) {
            instance = new OrganizationService();
        }
        return instance;
    }
    
    /**
     * Initialize with sample organizations
     */
    private void initializeSampleData() {
        organizations.add(new Organization("ORG001", "Hope Foundation", "123 Main St, Dhaka", "01712345678", "info@hope.org"));
        organizations.add(new Organization("ORG002", "Children's Care BD", "456 Park Ave, Chittagong", "01798765432", "contact@childrencare.org"));
        organizations.add(new Organization("ORG003", "Future Leaders", "789 School Rd, Sylhet", "01611223344", "support@futureleaders.org"));
    }
    
    /**
     * Get all organizations
     */
    public List<Organization> getAllOrganizations() {
        return new ArrayList<>(organizations);
    }
    
    /**
     * Get organization by ID
     */
    public Organization getOrganizationById(String organizationId) {
        Optional<Organization> org = organizations.stream()
                .filter(o -> o.getOrganizationId().equals(organizationId))
                .findFirst();
        return org.orElse(null);
    }
    
    /**
     * Get organization name by ID
     */
    public String getOrganizationName(String organizationId) {
        Organization org = getOrganizationById(organizationId);
        return org != null ? org.getName() : "Unknown Organization";
    }
}
