import java.util.Scanner;

public class ReportMenu {

public static void displayReportMenu(){

    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.println("------ Reports Menu ------");
        System.out.println("Choose and option: ");
        System.out.println("M) Month to Date");
        System.out.println("P) Previous Month");
        System.out.println("Y) Year to Date");
        System.out.println("S) Search by Vendor");
        System.out.println("B) Back to Ledger");
        System.out.println("H) Back to Home Page");

        String (choice) = sc.nextLine().trim();

        switch (choice) {
            case "M":
                System.out.println("Month to date report incoming ....");
                break;
            case "P":
                System.out.println("Previous month report incoming ....");
                break;
            case "Y":
                System.out.println("Year to date report incoming ....");
                break;
            case "S":
                searchByVendor();
                break;
            case "B":
                return;
                break;
            case "H":
                homePageReturn();
                break;
            default:
                System.out.println("Invalid choice, try again.");


        }


        public static void searchByVendor() {
            System.out.println("Enter vendor name to search: ");
            String vendorSearch = sc.nextLine().trim().toLowerCase();


            File file = new File("transactions.csv");
            if (!file.exist()) {
            System.out.println("No transactions available");
            return;
        }








    }


}




}
