package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
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

    //method for reading file and returns an ArrayList
    public static ArrayList<Transaction> readFromCSV() {
        ArrayList<Transaction> transaction = new ArrayList<>();

        //read the file line by line
        try {
            //create a file reader to read the file
            FileReader readFile = new FileReader("src/main/resources/transactions.csv");

            //create the buffered reader to read the file
            BufferedReader bufRead = new BufferedReader(readFile);

            //skips header row
            String header = bufRead.readLine();

            String line;

            //while loop
            while ((line = bufRead.readLine()) != null) {

                //split the line into different pieces
                String[] pieces = line.split("\\|");

                //if statement for pieces of the line
                if (pieces.length == 5) {

                    //parse the pieces of the line to be for the user to read
                    LocalDate date = LocalDate.parse(pieces[0].trim());
                    LocalTime time = LocalTime.parse(pieces[1].trim());
                    String description = pieces[2].trim();
                    String vendor = pieces[3].trim();
                    Double amount = Double.parseDouble(pieces[4].trim());

                    //create a new transaction
                    Transaction t = new Transaction(date, time, description, vendor, amount);

                    //add the product to our transaction ArrayList
                    transaction.add(t);
                }
            }
            //close the buffered reader so it can read the file
            bufRead.close();

        } catch (Exception e) {

            //if we run into an issue reading the file, display this instead
            System.out.println("Error reading the file ❌" + e.getMessage());
        }

        //return the transaction
        return transaction;
    }

    //method for writing to file
    public static void writeToCSV(Transaction transaction) {
        try {

            //create a file writer and set append to true so it adds to the file and not override its contents
            FileWriter csvFile = new FileWriter("src/main/resources/transactions.csv", true);

            //create the buffered writer to write to file
            BufferedWriter bufWriter = new BufferedWriter(csvFile);

            //create a date and time
            LocalDateTime time = LocalDateTime.now();

            //format time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formatTime = time.format(formatter);

            //format the buffered writer will put the transaction info in
            bufWriter.write(transaction.getDate() + " | " +
                    formatTime + " | " +
                    transaction.getDescription() + " | " +
                    transaction.getVendor() + " | " +
                    transaction.getAmount());

            //make sure we have a new line in our file
            bufWriter.newLine();
            //close the buffered writer so it actually writes to the file
            bufWriter.close();

        } catch (Exception e) {

            //if we run into an issue writing to the file, display this instead
            System.out.println("Error writing to the file: ❌" + e.getMessage());
        }
    }

    // method to display transactions with special formatting, so it doesn't print out weird
    public static void displayTransaction(Transaction t) {
        System.out.printf("%-12s | %-8s | %-20s | %-17s | %10.2f\n",
                t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
    }

    //method for fake user friendly header row
    public static void formatHeader() {
        System.out.println("\nDate         | Time     | Description          | Vendor            |     Amount");
        System.out.println("-------------|----------|----------------------|-------------------|------------");
    }

    //method for printing things out slowly
    public static void printSlowly() {

        //helps it print out the next thing slowly
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //method for R) Reports Screen
    public static void openReports() {

        boolean viewingReports = true;

        //while loop for viewing reports (all entries should show the newest entries first)
        while (viewingReports) {

            //display Reports Screen Submenu options
            printSlowly();
            System.out.println("\n=========== \uD83D\uDCCA Reports Menu ===========");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose a report option: ");
            String choice = sc.nextLine().trim();

            LocalDate today = LocalDate.now();

//            //sort transactions from newest to oldest
//            Collections.reverse(allTransactions);

            //switch statement for the Reports Screen based off what the user chooses
            switch (choice) {
                case "1":

                    System.out.println("\n=========== \uD83D\uDCC6 Month To Date Report ===========");
                    //fake user friendly header row
                    formatHeader();

                    //display transactions from this month
                    for (Transaction t : readFromCSV()) {
                        if (t.getDate().getMonth() == today.getMonth() &&
                                t.getDate().getYear() == today.getYear()) {
                            displayTransaction(t);
                        }
                    }
                    break;
                case "2":
                    System.out.println("\n=========== \uD83D\uDCC6 Previous Month Report ===========");
                    //fake user friendly header row
                    formatHeader();

                    //display transactions from last month
                    LocalDate lastMonth = today.minusMonths(1);

                    for (Transaction t : readFromCSV()) {
                        if (t.getDate().getMonth() == lastMonth.getMonth() &&
                                t.getDate().getYear() == lastMonth.getYear()) {
                            displayTransaction(t);
                        }
                    }
                    break;
                case "3":

                    System.out.println("\n=========== \uD83D\uDCC6 Year To Date Report ===========");
                    //fake user friendly header row
                    formatHeader();

                    //display transactions from this year
                    for (Transaction t : readFromCSV()) {
                        if (t.getDate().getYear() == today.getYear()) {
                            displayTransaction(t);
                        }
                    }
                    break;
                case "4":

                    System.out.println("\n=========== \uD83D\uDCC6 Previous Year Report ===========");
                    //fake user friendly header row
                    formatHeader();

                    //display transactions from last year
                    LocalDate lastYear = today.minusYears(1);

                    for (Transaction t : readFromCSV()) {
                        if (t.getDate().getYear() == lastYear.getYear()) {
                            displayTransaction(t);
                        }
                    }
                    break;
                case "5":

                    //prompt user for the vendor name and display all entries for that vendor
                    System.out.println("\n===================================");
                    System.out.print("Enter vendor name: ");
                    String vendorSearch = sc.nextLine().trim();

                    System.out.println("\n=========== \uD83D\uDCC2 Transactions for Vendor: " + vendorSearch + " ===========");
                    //fake user friendly header row
                    formatHeader();

                    for (Transaction t : readFromCSV()) {
                        if (t.getVendor().equalsIgnoreCase(vendorSearch)) {
                            displayTransaction(t);
                        }
                    }
                    break;
                case "0":
                    //go back to Ledger Screen
                    viewingReports = false;
                    break;

                default:
                    printSlowly();
                    System.out.println("Invalid selection ❌");
            }
        }
    }
}