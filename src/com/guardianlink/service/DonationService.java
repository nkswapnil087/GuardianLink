package com.guardianlink.service;

import com.guardianlink.model.entity.Donation;
import com.guardianlink.repository.DonationRepository;

import java.util.List;

/**
 * Service layer for Donation-related business logic
 */
public class DonationService {
    private static DonationService instance;
    private DonationRepository donationRepository;
    
    private DonationService() {
        donationRepository = DonationRepository.getInstance();
    }
    
    public static DonationService getInstance() {
        if (instance == null) {
            instance = new DonationService();
        }
        return instance;
    }
    
    /**
     * Get all donations
     */
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }
    
    /**
     * Get donations by donor
     */
    public List<Donation> getDonationsByDonor(String donorId) {
        return donationRepository.findByDonor(donorId);
    }
    
    /**
     * Get donations by child
     */
    public List<Donation> getDonationsByChild(String childId) {
        return donationRepository.findByChild(childId);
    }
    
    /**
     * Add new donation
     */
    public Donation addDonation(Donation donation) {
        donationRepository.save(donation);
        return donation;
    }
    
    /**
     * Get total donation amount
     */
    public double getTotalDonationAmount() {
        return donationRepository.getTotalDonationAmount();
    }
    
    /**
     * Generate next donation ID
     */
    public String generateNextDonationId() {
        return donationRepository.generateNextId();
    }
}
