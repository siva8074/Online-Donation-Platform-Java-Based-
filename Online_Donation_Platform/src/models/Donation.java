package models;


public class Donation {
    private String donorId;
    private String donorName;
    private double amount;
    public String category;

    public Donation(String donorId, String donorName, double amount, String category) {
        this.donorId = donorId;
        this.donorName = donorName;
        this.amount = amount;
        this.category = category;
    }

    public String getDonorId() {
        return donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}
