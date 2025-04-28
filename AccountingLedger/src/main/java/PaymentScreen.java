import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class PaymentScreen {
    Scanner sc = new Scanner(System.in);
    public static void displayPaymentScreen() {
        System.out.println("----- Make a payment -----");

        Scanner sc = new Scanner(System.in);

// Set the format for date and time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = null;
/*Take user date and time input and ensure correct format
        set loop to continue until valid input is received */
        while (ldt == null) {
            System.out.println("Enter date and time (yyyy-MM-dd HH:mm:ss): ");
            String dateTime = sc.nextLine();
            try {
                ldt = LocalDateTime.parse(dateTime, dtf);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please try again using yyyy-MM-dd HH:mm:ss");
            }
        }

        String datePart = ldt.toLocalDate().toString();
        String timePart = ldt.toLocalTime().toString();


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
            pw.println(datePart + "|" + timePart + "|" + description + "|" + vendor + "|" + amount);
        } catch (IOException e) {
            System.out.println("Unable to add transaction" + e.getMessage());
        }

    }

}








