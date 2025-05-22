package services;

import java.util.*;

public class LeaderboardService {
    private Map<String, Double> donorTotals = new HashMap<>();

    public void addDonation(String donorName, double amount) {
        donorTotals.put(donorName, donorTotals.getOrDefault(donorName, 0.0) + amount);
    }

    public void printTopDonors(int topN) {
        donorTotals.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(topN)
            .forEach(entry -> System.out.println("Donor: " + entry.getKey() + " - Amount: ₹" + entry.getValue()));
    }
}
