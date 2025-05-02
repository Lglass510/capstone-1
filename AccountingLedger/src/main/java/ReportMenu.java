import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ReportMenu {
    // ANSI escape codes for color
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String RESET = "\u001B[0m";


    static final Scanner sc = new Scanner(System.in);


    public static void displayReportMenu() {
        while (true) {
            System.out.println("""
                ------ Reports Menu ------
                Choose an Option:
                M) Month to Date
                P) Previous Month
                Y) Year to Date
                R) Previous Year
                S) Search by Vendor
                B) Back to ledger
                H) Back to Home Page
                """);
// Read and trim user input
            String choice = sc.nextLine().trim().toUpperCase();

            switch (choice) {
                case "M", "P", "Y", "R":
                    displayReport(choice);
                    break;
                case "S":
                    searchByVendor();
                    break;
                case "B":
                    return; // Back to ledger
                case "H":
                    return; // Back to home
                default:
                    System.out.println("Invalid choice, try again.");


            }
        }
    }

    private static void displayReport(String mode) {
        filterAndDisplayTransactions(mode);
    }

    // Allows searching all transactions for matching vendor name
    public static void searchByVendor() {
        System.out.println("Enter vendor name to search: ");
        String vendorSearch = sc.nextLine().trim().toUpperCase();

        File file = new File("transactions.csv");
        if (!file.exists()) {
            System.out.println("No transactions available");
            return;
        }
        List<String> lines = readTransactions(file);
        if (lines.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        Collections.reverse(lines); // Show most recent entries first
        boolean matchFound = false;

        for (String transaction : lines) {
            String[] transactionDetails = transaction.split("\\|");

            if (transactionDetails.length == 5) {
                String date = transactionDetails[0];
                String time = transactionDetails[1];
                String description = transactionDetails[2];
                String vendor = transactionDetails[3];
                String amountStr = transactionDetails[4];

                if (vendor.toUpperCase().contains(vendorSearch)) {
                    matchFound = true;
                    double amount = Double.parseDouble(amountStr);
                    String color = amount >= 0 ? GREEN : RED;
                    System.out.printf("%-12s   | %-9s  | %-20s | %-15s  | %s%10.2f%s\n",
                            date, time, description, vendor, color, amount, RESET);
                }

            }
            if (!matchFound){
                System.out.println("No transactions found for vendor: " + vendorSearch);

        }

    }

}

private static void filterAndDisplayTransactions(String mode) {
    File file = new File("transactions.csv");
    if (!file.exists()) {
        System.out.println("No transactions available.");
        return;
    }

    ArrayList<String> lines = readTransactions(file);
    if (lines.isEmpty()) {
        System.out.println("No transactions found.");
        return;
    }
    Collections.reverse(lines);
    displayHeader();

    LocalDate[] dateRange = determineDateRange(mode);
    if (dateRange == null) {
        System.out.println("Invalid report mode");
        return;
    }
    boolean found = false;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    for (String transaction : lines) {
        String[] details = transaction.split("\\|");
        if (details.length == 5) {
            try {
                LocalDate transactionDate = LocalDate.parse(details[0], dateFormatter);
                if (!transactionDate.isBefore(dateRange[0]) && !transactionDate.isAfter(dateRange[1])) {
                    found = true;
                    double amount = Double.parseDouble(details[4]);
                    String color = amount > 0 ? GREEN : RED;
                    displayTransaction(details, color);
                }
            } catch (DateTimeParseException | NumberFormatException e) {
                System.out.println("Skipping invalid entry: " + details[0]);
            }
        }
    }
    if (!found) {
        System.out.println("No transactions found in this time range");
    }
}

private static LocalDate[] determineDateRange(String mode) {
    LocalDate today = LocalDate.now();
    LocalDate startDate;
    LocalDate endDate = today;

    switch (mode) {
        case "M":
            startDate = today.withDayOfMonth(1); // First day of current month
            break;
        case "Y":
            startDate = today.withDayOfYear(1); // Jan 1st of current year
            break;
        case "P":
            LocalDate firstDayThisMonth = today.withDayOfMonth(1); //
            endDate = firstDayThisMonth.minusDays(1); // Last day of last month
            startDate = endDate.withDayOfMonth(1); // First day of last month
            break;
        case "R":
            startDate = today.minusYears(1).withDayOfYear(1);
            endDate = startDate.withMonth(12).withDayOfMonth(31);
            break;
        default:
            System.out.println("Invalid report mode.");
            return null;
    }
    return new LocalDate[]{startDate, endDate};
}

private static ArrayList<String> readTransactions(File file) {
    ArrayList<String> lines = new ArrayList<>();

    try (Scanner fileScanner = new Scanner(file)) {
        while (fileScanner.hasNextLine()) {
            lines.add(fileScanner.nextLine());
        }
    } catch (IOException e) {
        System.out.println("Error reading transactions: " + e.getMessage());

    }
    return lines;
}
    private static void displayHeader () {
        System.out.println("Date    | Time  | Description   | Vendor    | Amount");
        System.out.println("-----------------------------------------------------");
    }
    private static void displayTransaction (String[]details, String color){
        System.out.printf("%-12s | %-9s  | %-20s | %-15s  | %s%10.2f%s\n",
                details[0], details[1], details[2], details[3], color, Double.parseDouble(details[4]), RESET);
    }


}



