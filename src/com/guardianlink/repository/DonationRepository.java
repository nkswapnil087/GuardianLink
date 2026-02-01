package com.guardianlink.repository;

import com.guardianlink.model.entity.Donation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository for managing Donation data (in-memory storage)
 */
public class DonationRepository {
    private static DonationRepository instance;
    private List<Donation> donations;
    
    private DonationRepository() {
        donations = new ArrayList<>();
        initializeSampleData();
    }
    
    public static DonationRepository getInstance() {
        if (instance == null) {
            instance = new DonationRepository();
        }
        return instance;
    }
    
    /**
     * Initialize with sample donations for testing
     */
    private void initializeSampleData() {
        donations.add(new Donation("D001", "U004", "C001", 5000, "2026-01-15", "Bank Transfer"));
        donations.add(new Donation("D002", "U004", "C002", 8000, "2026-01-18", "Bank Transfer"));
        donations.add(new Donation("D003", "U004", "C004", 10000, "2026-01-20", "Credit Card"));
        donations.add(new Donation("D004", "U004", "C006", 6500, "2026-01-22", "Bank Transfer"));
        donations.add(new Donation("D005", "U004", "C002", 7000, "2026-01-24", "Mobile Banking"));
        donations.add(new Donation("D006", "U004", "C001", 5000, "2026-01-25", "Bank Transfer"));
        donations.add(new Donation("D007", "U004", "C004", 9000, "2026-01-26", "Credit Card"));
        donations.add(new Donation("D008", "U004", "C006", 15000, "2026-01-27", "Bank Transfer"));
    }
    
    /**
     * Find all donations
     */
    public List<Donation> findAll() {
        return new ArrayList<>(donations);
    }
    
    /**
     * Find donation by ID
     */
    public Optional<Donation> findById(String donationId) {
        return donations.stream()
                .filter(d -> d.getDonationId().equals(donationId))
                .findFirst();
    }
    
    /**
     * Find donations by donor
     */
    public List<Donation> findByDonor(String donorId) {
        return donations.stream()
                .filter(d -> d.getDonorId().equals(donorId))
                .collect(Collectors.toList());
    }
    
    /**
     * Find donations by child
     */
    public List<Donation> findByChild(String childId) {
        return donations.stream()
                .filter(d -> d.getChildId().equals(childId))
                .collect(Collectors.toList());
    }
    
    /**
     * Add new donation
     */
    public void save(Donation donation) {
        donations.add(donation);
    }
    
    /**
     * Delete donation
     */
    public boolean delete(String donationId) {
        return donations.removeIf(d -> d.getDonationId().equals(donationId));
    }
    
    /**
     * Generate next donation ID
     */
    public String generateNextId() {
        int maxId = donations.stream()
                .map(Donation::getDonationId)
                .map(id -> Integer.parseInt(id.substring(1)))
                .max(Integer::compareTo)
                .orElse(0);
        return String.format("D%03d", maxId + 1);
    }
    
    /**
     * Calculate total donations
     */
    public double getTotalDonationAmount() {
        return donations.stream()
                .mapToDouble(Donation::getAmount)
                .sum();
    }
}
