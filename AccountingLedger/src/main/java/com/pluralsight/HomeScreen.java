package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Loops until user exits
        while (true) {

            System.out.println("🤠💰 Howdy, partner! Welcome to *Glass Western National Bank*!");
            System.out.println("🌟 What would you like to do today?");
            System.out.println("--------------------------------------");
            System.out.println("💵 D) Add Deposit");
            System.out.println("💳 P) Make Payment (Debit)");
            System.out.println("📒 L) View Ledger");
            System.out.println("❌ X) Exit the Bank");
            System.out.println("--------------------------------------");
            System.out.print("👉 Make a selection: ");

            String input = sc.nextLine();
            char function = Character.toUpperCase(input.charAt(0));

            switch (function) {
                case 'D':
                    System.out.println("Welcome, you can make a deposit here! ");
                    DepositScreen.displayDepositScreen();
                    break;
                case 'P':
                    System.out.println("Welcome you can make a payment here!");
                    PaymentScreen.displayPaymentScreen();
                    break;
                case 'L':
                    System.out.println("Here is your current ledger");
                    LedgerScreen.displayLedgerScreen();
                    break;
                case 'X':
                    System.out.println("Exiting now .....");
                    return;
                default:
                    System.out.println("Please select a valid choice.");
            }
        }
    }

    static void displayHomeScreen() {
        System.out.println("Howdy! We are excited to serve you at Glass Western National Bank!");
        System.out.println("Please select one of the following options: \n");
        System.out.println("To make a deposit select: D ");
        System.out.println("To make a payment select: P ");
        System.out.println("To view ledger select: L ");
        System.out.println("To exit select: X ");
    }
}