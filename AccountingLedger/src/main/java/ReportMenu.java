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
        System.out.println("------ Reports Menu ------");
        System.out.println("Choose and option: ");
        System.out.println("M) Month to Date");
        System.out.println("P) Previous Month");
        System.out.println("Y) Year to Date");
        System.out.println("R) Previous Year");
        System.out.println("S) Search by Vendor");
        System.out.println("B) Back to Ledger");
        System.out.println("H) Back to Home Page");

// Read and trim user input
        String choice = sc.nextLine().trim().toUpperCase();

        switch (choice) {
            case "M":
                System.out.println("Month to date report incoming ....");
                monthToDateReport();
                break;
            case "P":
                System.out.println("Previous month report incoming ....");
                previousMonthReport();
                break;
            case "Y":
                System.out.println("Year to date report incoming ....");
                yearToDateReport();
                break;
            case "R":
                System.out.println("Previous year report incoming ....");
                previousYearReport();
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
// Allows searching all transactions for matching vendor name
        public static void searchByVendor() {
            System.out.println("Enter vendor name to search: ");
            String vendorSearch = sc.nextLine().trim().toUpperCase();


            File file = new File("transactions.csv");
            if (!file.exists()) {
                System.out.println("No transactions available");
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

                Collections.reverse(lines); // Show most recent entries first
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

                        if (vendor.toUpperCase().contains(vendorSearch)) {
                            matchFound = true;
                            double amount = Double.parseDouble(amountStr);
                            String color = amount >= 0 ? GREEN : RED;
                            System.out.printf("%-12s   | %-9s  | %-20s | %-15s  | %s%10.2f%s\n",
                                    date, time, description, vendor,color, amount, RESET);
                        }

                    }
                }

            if (!matchFound) {
                System.out.println("No transactions found for vendor: " + vendorSearch);
            }
        } catch (IOException e) {
        System.out.println("Error reading transactions: " + e.getMessage());
    }


}
// Filters by the first day of the current month up to today
public static void monthToDateReport() {
    filterAndDisplayTransactions("month");
}

// Filters by the first day of the current year up today
public static void yearToDateReport() {
    filterAndDisplayTransactions("year");

}
//Filters by the previous month up to the end of that month
public static void previousMonthReport() {
    filterAndDisplayTransactions("previousMonth");
}
// Filters by the full previous year
public static void previousYearReport(){
    filterAndDisplayTransactions("previousYear");
}
// Method for filtering based on time range
private static void filterAndDisplayTransactions(String mode) {
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
            System.out.println("No transactions found.");
            return;
        }
        Collections.reverse(lines);
        System.out.println("Date        | Time      | Description       | Vendor     | Amount");
        System.out.println("------------------------------------------------------------------");

        LocalDate today = LocalDate.now();
        LocalDate startDate;
        LocalDate endDate = today;

        switch (mode) {
            case "month":
                startDate = today.withDayOfMonth(1); // First day of current month
                break;
            case "year":
                startDate = today.withDayOfYear(1); // Jan 1st of current year
                break;
            case "previousMonth":
                LocalDate firstDayThisMonth = today.withDayOfMonth(1); //
                endDate = firstDayThisMonth.minusDays(1); // Last day of last month
                startDate = endDate.withDayOfMonth(1); // First day of last month
                break;
            case "previousYear":
                startDate = today.minusYears(1).withDayOfYear(1);
                endDate = startDate.withMonth(12).withDayOfMonth(31);
                break;
            default:
                System.out.println("Invalid report mode.");
                return;
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean found = false;

        for (String transaction : lines) {
            String[] transactionDetails = transaction.split("\\|");

            if (transactionDetails.length == 5) {
                String date = transactionDetails[0];
                String time = transactionDetails[1];
                String description = transactionDetails[2];
                String vendor = transactionDetails[3];
                String amountStr = transactionDetails[4];

                try {
                    LocalDate transactionDate = LocalDate.parse(date, dateFormatter);
                    if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(today)) {
                        found = true;
                        double amount = Double.parseDouble(amountStr);
  // ternary operator evaluates green if true and red if false
                        String color = amount >0 ? GREEN : RED;
                        System.out.printf("%-12s| %-9s  | %-20s | %-15s   | %s%10.2f%s\n",
                                date, time, description, vendor, color, amount, RESET);

                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Skipping invalid date entry: " + date);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid amount entry: " + amountStr);
                }

            }
        }

        if (!found) {
            System.out.println("No transactions found in this time range.");

        }

    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());

    }

}




}



