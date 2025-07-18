import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class DepositScreen {
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    // Returns current date as formatted
    private static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
// Returns current time as formatted
    private static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

    }
// Displays deposit screen and records the transaction
    public static void displayDepositScreen() {
        System.out.println("\n ----- Add a Deposit ------");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter description: ");
        String description = sc.nextLine();
        System.out.println("Enter Vendor: ");
        String vendor = sc.nextLine();
// Get and validate deposit amount
        double amount;
        while (true) {
            System.out.println("Enter amount: ");
            String amountInput = sc.nextLine();
            try {
                amount = Double.parseDouble(amountInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number (e.g., 50.00");
            }
        }
//Append new transactions to the file and set an exception for failure
            try (FileWriter fw = new FileWriter("transactions.csv", true);
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.println(getCurrentDate() + "|" + getCurrentTime() + "|" + description + "|" + vendor + "|" + amount);
            } catch (IOException e) {
                System.out.println("Unable to add transaction" + e.getMessage());
            }
   // Show user entries for confirmation
        System.out.printf("\nTransaction recorded:\n%-10s   | %-7s  | %-15s | %-8s  | %.2f%s\n",
                GREEN, getCurrentTime(), description, vendor, amount, RESET);
        System.out.println("\nWould you like to make another deposit? (Y/N)");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            displayDepositScreen();
        } else {
            System.out.println("\nReturning to Home Screen....");
           // HomeScreen.displayHomeScreen();
        }


    }



}





