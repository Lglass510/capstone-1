import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class LedgerScreen {
    static final Scanner sc = new Scanner(System.in);

    //ansi escape sequence to make negative entries red
    static final String RED = "\u001B[31m";
    //Reset the text back to normal color
    static final String RESET = "\u001B[0m";


    //Creating method to display ledger screen

    public static void displayLedgerScreen() {
        while (true) {
            System.out.println("----- Accounting Ledger -----");
        System.out.println("Choose an option:");
        System.out.println("A) All\nD) Deposits\nP) Payments\nR) Reports\nH) Home");
        String choice = sc.nextLine().trim().toUpperCase();



        switch (choice) {
            case "A":
                displayTransactions("ALL");
                break;
            case "D":
                displayTransactions("DEPOSIT");
                break;
            case "P":
                displayTransactions("PAYMENT");
                break;
            case "R":
                displayReportMenu();
                break;
            case "H":
                return;//HomeScreen
            default:
                System.out.println("Invalid choice.");
            }
        }
    }


// Display transactions with a filter
    private static void displayTransactions(String filterType) {
        File file = new File("transactions.csv");


//Return message if file can not be found
        if (!file.exists()) {
            System.out.println("No transactions available.");
            return;
        }

        ArrayList<String> lines = new ArrayList<>();

// Read all lines from the file
        try (Scanner sc = new Scanner(file)) {
            if (!sc.hasNextLine()) {
                System.out.println("No transactions available.");
                return;
            }

            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
// Show most recent transactions first
            Collections.reverse(lines);

// Read and display each transaction line from the file
            System.out.println("Date        | Time      | Description       | Vendor     | Amount");
            System.out.println("------------------------------------------------------------------");


            for (String transaction : lines) {
                String[] transactionDetails = transaction.split("\\|");


                if (transactionDetails.length == 5) {
                    String date = transactionDetails[0];
                    String time = transactionDetails[1];
                    String description = transactionDetails[2];
                    String vendor = transactionDetails[3];
                    String amountStr = transactionDetails[4];

                    try {
                        double amount = Double.parseDouble(amountStr);

 // Filtering logic
                        if (filterType.equals("ALL")){
                            //no filter show all
                        }
                        else if (filterType.equals("DEPOSIT")&& amount < 0) continue;
                        else if (filterType.equals("PAYMENT")&& amount > 0) continue;


                        if (amount < 0) {
                            //Negative amounts in red .... string RED amount with 2 dec places than another string RESET
                            System.out.printf("%-10s | %-7s  | %-15s | %-8s  | %s%.2f%s\n",
                                    date, time, description, vendor, RED, amount, RESET);
                        } else {
                            //Regular display for positive values
                            System.out.printf("%-10s| %-7s  | %-15s | %-8s   | %.2f\n",
                                    date, time, description, vendor, amount);
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing amount: " + amountStr);
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read transactions: " + e.getMessage());
        }
    }

    public static void searchByVendor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter vendor name to search: ");
        String vendorSearch = sc.nextLine().trim().toLowerCase();

        File file = new File("transactions.csv");
        if (!file.exists()) {
            System.out.println("No transactions available.");
            return;
        }

        ArrayList<String> lines = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }

            if (lines.isEmpty()) {
                System.out.println("No transactions available.");
                return;
            }

            Collections.reverse(lines);
            System.out.println("Date        | Time      | Description       | Vendor     | Amount");
            System.out.println("------------------------------------------------------------------");

            boolean matchFound = false;

            for (String transaction : lines) {
                String[] transactionDetails = transaction.split("\\|");

                if (transactionDetails.length == 5) {
                    String date = transactionDetails[0];
                    String time = transactionDetails[1];
                    String description = transactionDetails[2];
                    String vendor = transactionDetails[3];
                    String amountStr = transactionDetails[4];

                    if (vendor.toLowerCase().contains(vendorSearch)) {
                        matchFound = true;

                        try {
                            double amount = Double.parseDouble(amountStr);

                            if (amount < 0) {
                                //Negative amounts in red .... string RED amount with 2 dec places than another string RESET
                                System.out.printf("%-10s | %-7s  | %-15s | %-8s  | %s%.2f%s\n",
                                        date, time, description, vendor, RED, amount, RESET);
                            } else {
                                //Regular display for positive values
                                System.out.printf("%-10s| %-7s  | %-15s | %-8s   | %.2f\n",
                                        date, time, description, vendor, amount);
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing amount: " + amountStr);
                        }

                        }
                    }

                }
            if (!matchFound) {
                System.out.println("No transactions found for vendor: " + vendorSearch);

            }

        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

    } private static void displayReportMenu() {
        System.out.println("Report menu");
    }


}



