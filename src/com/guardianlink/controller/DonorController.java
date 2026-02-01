package com.guardianlink.controller;

import com.guardianlink.model.entity.Child;
import com.guardianlink.model.entity.Donation;
import com.guardianlink.model.user.Donor;
import com.guardianlink.service.ChildService;
import com.guardianlink.service.DonationService;

import java.util.List;

/**
 * Controller for Donor-specific operations
 */
public class DonorController {
    private static DonorController instance;
    private ChildService childService;
    private DonationService donationService;
    
    private DonorController() {
        childService = ChildService.getInstance();
        donationService = DonationService.getInstance();
    }
    
    public static DonorController getInstance() {
        if (instance == null) {
            instance = new DonorController();
        }
        return instance;
    }
    
    /**
     * Get all children available for sponsorship
     */
    public List<Child> getAvailableChildren() {
        return childService.getAllChildren();
    }
    
    /**
     * Make a donation
     */
    public boolean makeDonation(Donor donor, String childId, double amount, String paymentMethod) {
        String donationId = donationService.generateNextDonationId();
        String date = java.time.LocalDate.now().toString();
        
        Donation donation = new Donation(donationId, donor.getUserId(), childId, amount, date, paymentMethod);
        donationService.addDonation(donation);
        
        // Update donor's total donated amount
        donor.setTotalDonated(donor.getTotalDonated() + amount);
        
        return true;
    }
    
    /**
     * Get donation history for a donor
     */
    public List<Donation> getDonationHistory(String donorId) {
        return donationService.getDonationsByDonor(donorId);
    }
}
