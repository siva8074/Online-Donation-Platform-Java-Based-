package services;

import models.*;
import exceptions.*;
import java.util.*;

public class DonationService {
    private Map<String, Donor> donorMap = new HashMap<>();
    private List<Donation> donationList = new ArrayList<>();

    public void addDonation(String name, String email, String category, double amount) throws InvalidDonationAmountException {
        if (amount < 100) throw new InvalidDonationAmountException("Minimum donation is ₹100");
        String donorId = "donor" + (donorMap.size() + 1);
        Donor donor = new Donor(donorId, name, email);
        donorMap.put(donorId, donor);
        donationList.add(new Donation(donorId, name, amount, category));
        System.out.println("Thank you for your donation, " + name);
        System.out.println("ID: " + donorId + ", Category: " + category + ", Amount: ₹" + amount);
    }

    public List<Donation> getDonationsByCategory(String category) {
        List<Donation> filtered = new ArrayList<>();
        for (Donation d : donationList) {
            if (d.category.equalsIgnoreCase(category)) {
                filtered.add(d);
            }
        }
        return filtered;
    }

    public List<Donation> getAllDonations() {
        return donationList;
    }
}