import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DepositScreen {
    public static void displayDepositScreen() {
        System.out.println("\n ----- Add a Deposit ------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        System.out.println("Enter time (HH:MM:SS): ");
        String time = sc.nextLine();
        System.out.println("Enter description: ");
        String description = sc.nextLine();
        System.out.println("Enter Vendor: ");
        String vendor = sc.nextLine();
        System.out.println("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();


        try (FileWriter fw = new FileWriter("transactions.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
        }
        catch (IOException e) {
            System.out.println("Unable to add transaction" + e.getMessage());
        }
    }






}
