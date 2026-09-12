class FeeAccount {
    public void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    public void pay(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class AccountBatchPaymentApp {

    // Dispatches via instanceof and updates counters[cite: 5]
    public static void processPayment(FeeAccount account, double amount) {
        account.pay(amount);
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        double paymentAmount = 60000.0;
        int hostelCount = 0;
        int dayScholarCount = 0;

        for (FeeAccount account : accounts) {
            if (account instanceof HostelFeeAccount) {
                hostelCount++;
            } else {
                dayScholarCount++;
            }
            processPayment(account, paymentAmount);
        }

        System.out.printf("Hostel accounts processed: %d | Day-scholar accounts processed: %d\n",
                hostelCount, dayScholarCount);
    }
}