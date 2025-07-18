package com.pluralsight;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String vendor;
    private double amount;
    private String type;
    private LocalDateTime timestamp;

    public Transaction(String vendor, double amount) {
        this.vendor = vendor;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return amount >= 0 ? "Deposit" : "Payment";
    }
}