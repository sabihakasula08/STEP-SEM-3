import java.util.HashMap;
import java.util.Map;

abstract class BankAccount {
    protected String id;
    protected double balance;

    public BankAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void withdraw(double amount);
}

class SavingsAccount extends BankAccount {
    public SavingsAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (this.balance - amount < 1000) {
            System.out.printf("%s rejected: minimum balance 1000\n", id);
        } else {
            this.balance -= amount;
            System.out.printf("%s balance %.0f\n", id, balance);
        }
    }
}

class CurrentAccount extends BankAccount {
    public CurrentAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (this.balance - amount < -5000) {
            System.out.printf("%s rejected: overdraft limit 5000\n", id);
        } else {
            this.balance -= amount;
            System.out.printf("%s balance %.0f\n", id, balance);
        }
    }
}

public class BankAccountWithdrawalApp {
    public static void main(String[] args) {
        Map<String, BankAccount> accounts = new HashMap<>();

        // Operations matching Sample Input[cite: 60]
        String[] operations = {
            "Savings S1 5000",
            "WITHDRAW S1 4500",
            "Current C1 2000",
            "WITHDRAW C1 6000",
            "WITHDRAW S1 3000",
            "WITHDRAW C1 2000"
        };

        for (String op : operations) {
            String[] tokens = op.split(" ");
            String cmd = tokens[0];

            if (cmd.equalsIgnoreCase("Savings")) {
                accounts.put(tokens[1], new SavingsAccount(tokens[1], Double.parseDouble(tokens[2])));
            } else if (cmd.equalsIgnoreCase("Current")) {
                accounts.put(tokens[1], new CurrentAccount(tokens[1], Double.parseDouble(tokens[2])));
            } else if (cmd.equalsIgnoreCase("WITHDRAW")) {
                String id = tokens[1];
                double amt = Double.parseDouble(tokens[2]);
                BankAccount acc = accounts.get(id);
                if (acc == null) {
                    System.out.println("Account not found");
                } else {
                    acc.withdraw(amt);
                }
            }
        }
    }
}