import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class LedgerScreen {

//ansi escape sequence to make negative entries red
  static final  String RED = "\u001B[31m";
  static final String RESET = "\u001B[0m";



    //Creating method to display ledger screen
    public static void displayLedgerScreen(){
        System.out.println("----- Accounting Ledger -----");
        File file = new File("transactions.csv");

//Return message if file can not be found
        if(!file.exists()) {
            System.out.println("No transactions available.");
            return;
        }

      //Return message if no transactions in the file can be found
        ArrayList<String> lines = new ArrayList<>();


        try (Scanner sc = new Scanner(file)) {
            if (!sc.hasNextLine()) {
                System.out.println("No transactions available.");
                return;
            }

            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
//Utility to reverse list
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
                        if (amount < 0) {
                            //Negative amounts in red
                            System.out.printf("%-10s | %-7s  | %-15s | %-8s  | %s%.2f\n",
                                     date, time, description, vendor, RED, amount, RESET );
                        } else {
                            //Regular display for positive values
                            System.out.printf("%-10s| %-7s  | %-15s | %-8s   | %.2f\n",
                                    date, time, description, vendor, amount);}

                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing amount: " + amountStr);
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read transactions: " + e.getMessage());
        }
        }



}



