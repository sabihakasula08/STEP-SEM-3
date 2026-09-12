class PayrollEmployee {
    private String empId;
    private double salary;

    // Resolves genuine field/parameter naming clashes using this[cite: 5]
    public PayrollEmployee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    // Applies raise using this.salary to resolve parameter clash[cite: 5]
    public void raiseSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Raise amount cannot be negative.");
        }
        this.salary = this.salary + salary;
    }

    public void printFinalSalary() {
        System.out.printf("%s | Final Salary: Rs %.1f\n", this.empId, this.salary);
    }
}

public class PayrollBatchBonusApp {
    public static void main(String[] args) {
        PayrollEmployee[] employees = {
            new PayrollEmployee("E-101", 40000.0),
            new PayrollEmployee("E-102", 55000.0),
            new PayrollEmployee("E-103", 62000.0),
            new PayrollEmployee("E-104", 48000.0)
        };

        double bonusAmount = 5000.0;

        // Apply identical bonus in a single pass[cite: 5]
        for (PayrollEmployee emp : employees) {
            emp.raiseSalary(bonusAmount);
            emp.printFinalSalary();
        }
    }
}