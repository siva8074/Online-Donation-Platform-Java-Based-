package main;

import java.util.*;
import models.*;
import services.*;
import exceptions.*;

public class DonationApp {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Map<String, Donor> donorMap = new HashMap<>();
		List<Donation> donationList = new ArrayList<>();
		LeaderboardService leaderboardService = new LeaderboardService();

		while (true) {
			System.out.println("\n--------------------------Online donation----------------------------");
			System.out.println("1. Donate amount ");
			System.out.println("2. Live leaderboard of top donors(10). ");
			System.out.println("3. Filter donations by category (e.g., education, healthcare) ");
			System.out.println("4. Generate monthly donation reports. ");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");
			int ch = sc.nextInt();
			sc.nextLine();

			switch (ch) {
				case 1:
					try {
						System.out.print("\nEnter donor name: ");
						String name = sc.nextLine();
						System.out.print("Enter donor email: ");
						String email = sc.nextLine();
						System.out.print("Enter donation category (e.g., education, healthcare): ");
						String category = sc.nextLine();
						System.out.print("Enter donation amount: ");
						double amount = sc.nextDouble();
						if (amount < 100) {
							throw new InvalidDonationAmountException("Minimum donation is ₹100.");
						}

						String donorId = email;
						Donor donor = donorMap.getOrDefault(donorId, new Donor(donorId, name, email));
						donorMap.putIfAbsent(donorId, donor);
						Donation donation = new Donation(donorId, name, amount, category);
						donationList.add(donation);
						leaderboardService.addDonation(donorId, amount);

						System.out.println("Thank you for your donation, " + name);
						System.out.println("\t\tID\t\tName\t\tCategory\t\tAmount");
						System.out.printf("\t\t%s\t\t%s\t\t%s\t\t₹%.2f\n", donorId, name, category, amount);
						System.out.println("You have been added to the top donor leaderboard.");
					} catch (InvalidDonationAmountException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;

				case 2:
					System.out.println("\nTop Donors:");
					leaderboardService.printTopDonors(10);
					break;

				case 3:
					System.out.print("Enter category to filter: ");
					String filterCategory = sc.nextLine();
					donationList.stream()
						.filter(d -> d.getCategory().equalsIgnoreCase(filterCategory))
						.forEach(d -> System.out.printf("%s - %s - ₹%.2f\n", d.getDonorName(), d.getCategory(), d.getAmount()));
					break;

				case 4:
					System.out.println("\nMonthly Donation Report:");
					donationList.forEach(d -> System.out.printf("%s - %s - %s - ₹%.2f\n", d.getDonorId(), d.getDonorName(), d.getCategory(), d.getAmount()));
					break;

				case 5:
					System.out.println("Thank you for using the Online Donation Platform.");
					return;

				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
