import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LedgerScreen {
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
        try (Scanner sc = new Scanner(file)) {
            if (!sc.hasNextLine()) {
                System.out.println("No transactions available.");
                return;
            }

// Read and display each transaction line from the file
            System.out.println("Date        | Time      | Description       | Vendor     | Amount");
            System.out.println("-----------------------------------------------------");
            while (sc.hasNextLine()) {
                String transaction = sc.nextLine();
                String[] transactionDetails = transaction.split("\\|");

                if (transactionDetails.length == 5) {
                    String date = transactionDetails[0];
                    String time = transactionDetails[1];
                    String description = transactionDetails[2];
                    String vendor = transactionDetails[3];
                    String amount = transactionDetails[4];

                    //Print in a nice and easy to read format
                    System.out.printf("%-10s | %-7s | %-15s | %-8s | %.2f%n",
                            date, time, description, vendor, amount);

                }
            }

        } catch (IOException e) {
            System.out.println("Unable to read transactions: " + e.getMessage());
        }
        }



}



