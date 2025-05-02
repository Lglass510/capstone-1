# AccountingLedger

A simple CLI-based Java application for tracking financial transactions like deposits and payments. It supports persistent storage via a `transactions.csv` file, filtering options, and basic reporting.

---

## 📁 Project Structure

```
AccountingLedger/
├── src/
│   └── main/
│       ├── java/
│       │   └── (Java source files)
│       └── resources/
│           ├── README.md
│           └── transactions.csv
```

---

## 🧠 Core Features

- **Add Deposits** – Automatically timestamps and records a deposit.
- **Make Payments** – Stores payment as a negative number for easy balance tracking.
- **View Ledger** – Shows all transactions, deposits, or payments.
- **Reports** – Includes:
  - Month To Date
  - Previous Month
  - Year To Date
  - Previous Year
  - Search by Vendor
  - Custom Search (Advanced filter)
- **CSV Persistence** – Saves and loads from `transactions.csv` using pipe `|` separators.

---

## 🚀 How It Works

Each transaction is stored like this:

```
2025-04-30|14:23:01|Deposit|Savings|1500.00
```

On launch, the program reads all entries from `transactions.csv` and loads them into memory. Every new transaction is appended to the same file.

---

## 🖼 Screenshots

### Home Screen
![Home Screen](images/home-screen.png)

### Deposit Screen
![Deposit](images/deposit-screen.png)

### Ledger
![Ledger](images/ledger-screen.png)

---

## 🛠 Tech Stack

- Java
- File I/O
- ArrayList
- Object-Oriented Programming (OOP)

---

## ✅ To Do

- [x] Add timestamp automation
- [x] Add payment formatting
- [ ] Implement GUI (later version)
- [ ] Add unit tests
- [ ] Improve custom search UI

---

## 🤝 Contributing

Pull requests welcome! For major changes, please open an issue first to discuss what you’d like to change.

---

## 📜 License

MIT License – do whatever you want with it.
