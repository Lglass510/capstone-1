import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class PaymentScreen {


    private static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }




    public static void displayPaymentScreen() {

        System.out.println("----- Make a payment -----");

        Scanner sc = new Scanner(System.in);


        System.out.println("Enter description: ");
        String description = sc.nextLine();
        System.out.println("Enter Vendor: ");
        String vendor = sc.nextLine();
//Take user input and convert to a double, stay in loop until valid input received
        double amount = 0;
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
            pw.println(getCurrentDate() + "|" + getCurrentTime() + "|" + description + "|" + vendor + "|" + -amount);
        } catch (IOException e) {
            System.out.println("Unable to add transaction" + e.getMessage());
        }
        System.out.printf("\nTransaction recorded:\n%-10s   | %-7s  | %-15s | %-8s  | %.2f\n", getCurrentDate(), getCurrentTime(), description, vendor, -amount);

    }


}








