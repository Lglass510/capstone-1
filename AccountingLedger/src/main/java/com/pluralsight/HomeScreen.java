package com.pluralsight;

import java.util.Scanner;



public class HomeScreen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager transactionManager = new TransactionManager();
        GoalTracker goalTracker = new GoalTracker();
        QuoteGenerator quoteGenerator = new QuoteGenerator();

        // Quote when app opens
        System.out.println("Welcome to FlexFund App!");
        System.out.println("\"" + quoteGenerator.getRandomFunnyQuote() + "\"");

        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add a deposit");
            System.out.println("2. Add a payment");
            System.out.println("3. View all transactions");
            System.out.println("4. View savings and spending progress");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter vendor name: ");
                    String depositVendor = scanner.nextLine();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    transactionManager.addTransaction(depositVendor, depositAmount);
                    break;
                case 2:
                    System.out.print("Enter vendor name: ");
                    String paymentVendor = scanner.nextLine();
                    System.out.print("Enter payment amount: ");
                    double paymentAmount = scanner.nextDouble();
                    scanner.nextLine();
                    transactionManager.addTransaction(paymentVendor, -paymentAmount);
                    break;
                case 3:
                    transactionManager.listTransactions();
                    break;
                case 4:
                   goalTracker.checkGoals(transactionManager.getBalance());
                    break;
                case 5:
                    running = false;
                    System.out.println("\nThank you for using FlexFund! Here's a quote for you:");
                    System.out.println("\"" + quoteGenerator.getRandomMotivationalQuote() + "\"");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            if (transactionManager.getTransactionCount() % 5 == 0 && transactionManager.getTransactionCount() != 0) {
                System.out.println("\nQuick Check: Your available balance is $" + transactionManager.getBalance());
                goalTracker.checkGoals(transactionManager.getBalance());
            }
        }

        scanner.close();
    }
}
