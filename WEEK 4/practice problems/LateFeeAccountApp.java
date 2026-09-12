class StudentAccount {
    private String regNo;
    private double totalFee;

    public StudentAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    // Locked against overriding by any subclass[cite: 5]
    public final double calculateLateFee(int daysLate) {
        // Late fee is calculated as 1% of total fee per day late
        return daysLate * (this.totalFee * 0.01);
    }

    // Locked against overriding by any subclass[cite: 5]
    public final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.printf("%s - On time, no late fee\n", this.regNo);
        } else {
            double lateFee = calculateLateFee(daysLate);
            System.out.printf("%s | Total Fee: Rs %.1f | Late Fee: Rs %.1f\n",
                    this.regNo, this.totalFee, lateFee);
        }
    }
}

public class LateFeeAccountApp {
    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000.0, 150000.0, 180000.0, 220000.0};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            StudentAccount account = new StudentAccount(regNos[i], totalFees[i]);
            account.printSummary(daysLate[i]);
        }
    }
}