/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.samissua.gymsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author samissua
 */
public class GymSystem {

    private static final Scanner scanner = new Scanner(System.in);
    static Map<MembershipType, List<Feature>> availableFeatures = new HashMap<>();

    public static void main(String[] args) {
        initializePlans();
        int result = startApplication();
        System.out.println("Final Result: " + result);
    }

    private static void initializePlans() {
        availableFeatures.put(MembershipType.BASIC, Arrays.asList(
                new Feature("Group Classes", 20),
                new Feature("Locker Room Access", 10)
        ));

        availableFeatures.put(MembershipType.PREMIUM, Arrays.asList(
                new Feature("Personal Trainer", 50),
                new Feature("Spa Access", 40),
                new Feature("Specialized Training", 60)
        ));

        availableFeatures.put(MembershipType.FAMILY, Arrays.asList(
                new Feature("Kids Zone", 25),
                new Feature("Family Group Classes", 30)
        ));

    }

    static int startApplication() {
        try {
            System.out.println("Welcome to the Gym Membership Management System");

            MembershipType selectedPlan = selectMembership();
            if (selectedPlan == null) {
                System.out.println("Invalid membership selected.");
                System.exit(-1);
            }

            List<Feature> selectedFeatures = selectFeatures(selectedPlan);

            System.out.println("Enter number of members signing up:");
            int memberCount = getValidatedIntegerInput();
            if (memberCount <= 0) {
                System.out.println("Number of members must be positive.");
                System.exit(-1);
            }

            boolean includesPremium = selectedPlan == MembershipType.PREMIUM;
            int totalCost = calculateTotalCost(selectedPlan, selectedFeatures, memberCount, includesPremium);

            System.out.println("Selected Membership: " + selectedPlan);
            System.out.println("Selected Features:");
            for (Feature f : selectedFeatures) {
                System.out.println("- " + f.getName() + " ($" + f.getPrice() + ")");
            }
            System.out.println("Total Cost: $" + totalCost);
            System.out.println("Confirm membership? (yes/no):");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                System.out.println("Membership confirmed. Final Cost: $" + totalCost);
            } else {
                System.out.println("Membership cancelled.");
                System.exit(-1);
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            System.exit(-1);
        }
        return 0;

    }

    private static MembershipType selectMembership() {
        System.out.println("Available Membership Plans:");
        for (MembershipType type : MembershipType.values()) {
            System.out.println(type + " - $" + type.getBaseCost());
        }
        System.out.println("Enter membership plan:");
        String input = scanner.nextLine().toUpperCase();
        try {
            return MembershipType.valueOf(input);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static List<Feature> selectFeatures(MembershipType type) {
        List<Feature> features = availableFeatures.get(type);
        List<Feature> selected = new ArrayList<>();
        System.out.println("Available Features:");
        for (int i = 0; i < features.size(); i++) {
            System.out.println((i + 1) + ". " + features.get(i).getName() + " ($" + features.get(i).getPrice() + ")");
        }
        System.out.println("Enter feature numbers separated by commas (or press Enter to skip):");
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return selected;
        }

        try {
            String[] indices = input.split(",");
            for (String index : indices) {
                int idx = Integer.parseInt(index.trim()) - 1;
                if (idx >= 0 && idx < features.size()) {
                    selected.add(features.get(idx));
                } else {
                    System.out.println("Invalid feature selection.");
                    System.exit(-1);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values.");
            System.exit(-1);
        }

        return selected;
    }

    private static int getValidatedIntegerInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static int calculateTotalCost(MembershipType type, List<Feature> features, int memberCount, boolean premium) {
        int base = type.getBaseCost();
        int featureCost = features.stream().mapToInt(Feature::getPrice).sum();
        double total = base + featureCost;

        if (memberCount >= 2) {
            System.out.println("Group discount applied (10%)");
            total *= 0.9;
        }

        if (total > 400) {
            System.out.println("Special offer: $50 discount applied");
            total -= 50;
        } else if (total > 200) {
            System.out.println("Special offer: $20 discount applied");
            total -= 20;
        }

        if (premium) {
            System.out.println("Premium surcharge (15%) applied");
            total *= 1.15;
        }

        return (int) Math.round(total);
    }
}

