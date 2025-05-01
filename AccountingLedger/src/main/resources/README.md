# 💰 Java CLI Financial Transaction Tracker

This is a command-line Java application that helps users track financial transactions including deposits and payments. The application reads from and writes to a `transactions.csv` file, and provides users with a simple interface to manage their ledger and view detailed reports.

---

## 🚀 Features

- ➕ Add deposits and ➖ make payments with automatic date and time stamping
- 📓 View a complete ledger of all transactions
- 🔍 Filter transactions by:
  - 💵 Deposits only
  - 💳 Payments only
- 📊 Generate reports:
  - 📆 Month to Date
  - 🗓️ Previous Month
  - 📅 Year to Date
  - 🧾 Previous Year
  - 🏷️ Search by Vendor
- 🎨 Color-coded output:
  - 🟢 Green for deposits
  - 🔴 Red for payments

---

## 🖼️ Screenshots

Below are screenshots of the key screens of the application:

1. 🏠 **Home Screen**
   ![image alt](<img width="558" alt="homescreen" src="https://github.com/user-attachments/assets/e8b406e5-dd81-4c59-9cb9-c3adfcd16270" />
)
   

3. 📒 **Ledger Screen**  
   ![imagealt](<img width="559" alt="ledgerscreen" src="https://github.com/user-attachments/assets/aba7631e-2c37-492b-b40c-9e41ebbc8fe0" />
)

4. 💵 **Deposit Screen**  
   ![imagealt](<img width="564" alt="depositscreen" src="https://github.com/user-attachments/assets/a8aed9af-2752-474c-80ef-f990837bcf19" />
)

5. 💳 **Payment Screen**  
   ![imagealt](<img width="536" alt="paymentscreen" src="https://github.com/user-attachments/assets/f8bdb09a-83ce-47c8-88c7-de3ab6a47553" />
)

6. 📊 **Report Screen**  
  ![imagealt<img width="546" alt="reportscreen" src="https://github.com/user-attachments/assets/ffb51eea-7943-482b-8cf3-5831e18a766a" />


---

## 🔍 Interesting Code Highlight
![imagealt](<img width="767" alt="ansicolors" src="https://github.com/user-attachments/assets/8b68f482-2e06-4b15-9376-0cce40601c54" />
)

Here’s a snippet and a photo that shows how the application filters and displays transactions by date, with color-coded output for clarity:

```java
LocalDate transactionDate = LocalDate.parse(date, dateFormatter);
if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(today)) {
    double amount = Double.parseDouble(amountStr);
    String color = amount > 0 ? GREEN : RED;
    System.out.printf("%-12s| %-9s  | %-20s | %-15s   | %s%10.2f%s\n",
        date, time, description, vendor, color, amount, RESET);
}
