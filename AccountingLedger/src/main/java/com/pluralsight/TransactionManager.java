package com.pluralsight;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List <Transaction> transactions = new ArrayList<>();

    public void addTransaction(String vendor, double amount){
        Transaction transaction = new Transaction(vendor, amount);
        transactions.add(transaction);
        System.out.println(transaction.getType() + " recorded for " + vendor + ": $" + Math.abs(amount));
    }

    public void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t.getType() + " | Vendor: " + t.getVendor() + " | Amount: $" + Math.abs(t.getAmount()));
            }
        }
    }

    public double getBalance() {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }

    public int getTransactionCount() {
        return transactions.size();
    }



}
