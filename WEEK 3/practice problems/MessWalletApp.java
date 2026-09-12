class MessWallet {
    // Private balance field strictly encapsulated
    private double balance;

    public MessWallet(double openingBalance) {
        if (openingBalance < 0) {
            System.out.println("Warning: Negative opening balance provided. Starting at 0.0.");
            this.balance = 0.0;
        } else {
            this.balance = openingBalance;
        }
    }

    // Adds amount if positive; rejects non-positive top-up
    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Top-up rejected: amount must be greater than zero.");
            return;
        }
        this.balance += amount;
        System.out.println("Balance after top-up: " + this.balance);
    }

    // Deducts amount if sufficient funds exist
    public void deduct(double amount) {
        if (amount <= 0) {
            System.out.println("Deduct rejected: amount must be greater than zero.");
            return;
        }
        if (amount > this.balance) {
            System.out.println("Deduct rejected: insufficient balance");
            return;
        }
        this.balance -= amount;
        System.out.println("Balance after deduction: " + this.balance);
    }

    // Read-only getter for balance
    public double getBalance() {
        return this.balance;
    }
}

public class MessWalletApp {
    public static void main(String[] args) {
        MessWallet wallet = new MessWallet(500.0);

        wallet.topUp(200.0);
        wallet.deduct(1000.0);

        System.out.println("Final balance: " + wallet.getBalance());
    }
}