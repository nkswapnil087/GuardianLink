package com.guardianlink.controller;

import com.guardianlink.model.entity.Organization;
import com.guardianlink.model.user.OrganizationAdmin;
import com.guardianlink.model.user.SystemAdmin;
import com.guardianlink.model.user.User;
import com.guardianlink.service.OrganizationService;
import com.guardianlink.service.UserService;

import java.util.List;

/**
 * Controller for organization management operations
 * Only System Admins can perform these operations
 */
public class OrganizationController {
    private static OrganizationController instance;
    private OrganizationService organizationService;
    private UserService userService;

    private OrganizationController() {
        organizationService = OrganizationService.getInstance();
        userService = UserService.getInstance();
    }

    public static OrganizationController getInstance() {
        if (instance == null) {
            instance = new OrganizationController();
        }
        return instance;
    }

    /**
     * Add new organization - Only System Admin
     */
    public Organization addOrganization(User user, String name, String address, String phone, String email) {
        if (!(user instanceof SystemAdmin)) {
            throw new IllegalArgumentException("Only System Admins can add organizations");
        }
        return organizationService.addOrganization(name, address, phone, email);
    }

    /**
     * Get all organizations
     */
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    /**
     * Delete organization - Only System Admin
     */
    public boolean deleteOrganization(User user, String organizationId) {
        if (!(user instanceof SystemAdmin)) {
            throw new IllegalArgumentException("Only System Admins can delete organizations");
        }
        return organizationService.deleteOrganization(organizationId);
    }

    /**
     * Get organization by ID
     */
    public Organization getOrganizationById(String organizationId) {
        return organizationService.getOrganizationById(organizationId);
    }

    /**
     * Assign organization admin to an organization - Only System Admin
     */
    public OrganizationAdmin assignOrgAdmin(User user, String userId, String username, String password, 
                                             String fullName, String email, String organizationId) {
        if (!(user instanceof SystemAdmin)) {
            throw new IllegalArgumentException("Only System Admins can assign organization admins");
        }
        
        // Create new OrganizationAdmin user
        OrganizationAdmin orgAdmin = new OrganizationAdmin(userId, username, password, fullName, email, organizationId);
        userService.registerUser(orgAdmin);
        return orgAdmin;
    }

/**
     * Get organization admins for a specific organization
     */
    public List<OrganizationAdmin> getOrgAdminsForOrganization(String organizationId) {
        return userService.getOrgAdminsByOrganization(organizationId);
