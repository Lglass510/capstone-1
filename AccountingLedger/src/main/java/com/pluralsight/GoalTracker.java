package com.pluralsight;

public class GoalTracker {
    private double savingsGoal = 1000.0; // Example goal
    private double spendingLimit = 500.0; // Example spending limit

    public void checkGoals(double currentBalance) {
        System.out.println("Savings Goal: $" + savingsGoal);
        System.out.println("Spending Limit: $" + spendingLimit);

        if (currentBalance >= savingsGoal) {
            System.out.println("🎉 Congratulations! You've reached your savings goal!");
        } else if (currentBalance <= spendingLimit) {
            System.out.println("⚠️ Warning: You are close to your spending limit. Be cautious!");
        } else {
            System.out.println("Keep going! You're doing great!");
        }
    }
}