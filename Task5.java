import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankApp {
    String name;
    double balance = 0;
    List<String> history = new ArrayList<>();

    public BankApp(String name) {
        this.name = name;
    }

    void deposit(double amt) {
            LocalDateTime now = LocalDateTime.now();
            String dt = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"));
        if (amt > 0) {
            balance += amt;
            history.add(dt + " - Deposit : " + amt);
            System.out.println("Deposited " + amt);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    void withdraw(double amt) {
        LocalDateTime now = LocalDateTime.now();
            String dt = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"));
        if (amt <= 0) {
            System.out.println("Invalid amount.");
        } else if (amt > balance) {
            System.out.println("Not enough balance.");
        } else {
            balance -= amt;
            history.add(dt + " - Withdraw: " + amt);
            System.out.println("Withdrawn " + amt);
        }
    }

    void showBalance() {
        System.out.println("Balance: " + balance);
    }

    void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions.");
        } else {
            System.out.println("Transaction History:");
            for (String h : history) {
                System.out.println(h);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String user = sc.nextLine();

        BankApp acc = new BankApp(user);
        int choice;

        while (true) {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance");
            System.out.println("4. History");
            System.out.println("0. Exit");
            System.out.print("Select: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                acc.deposit(amt);
            } else if (choice == 2) {
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                acc.withdraw(amt);
            } else if (choice == 3) {
                acc.showBalance();
            } else if (choice == 4) {
                acc.showHistory();
            } else if (choice == 0) {
                System.out.println("Exited");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
