class PayrollAccount {
    // Encapsulated private fields preventing unauthorized external mutation
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double openingBasicSalary) {
        if (openingBasicSalary < 0) {
            System.out.println("Warning: Negative opening salary provided. Starting at 0.0.");
            this.basicSalary = 0.0;
        } else {
            this.basicSalary = openingBasicSalary;
        }
        this.bonus = 0.0;
    }

    // Credits bonus if positive; rejects otherwise
    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Credit bonus rejected: amount must be greater than zero.");
            return;
        }
        this.bonus += amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    // Reduces basicSalary by percentage within 0-100% boundary
    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Deduct tax rejected: percentage must be between 0 and 100.");
            return;
        }
        double taxAmount = (this.basicSalary * percent) / 100.0;
        this.basicSalary -= taxAmount;
        System.out.printf("Tax deducted: %.0f%%\n", percent);
    }

    // Read-only getter for combined net salary
    public double getNetSalary() {
        return this.basicSalary + this.bonus;
    }
}

public class PayrollAccountApp {
    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000.0);

        account.creditBonus(5000.0);
        account.deductTax(10.0);

        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}