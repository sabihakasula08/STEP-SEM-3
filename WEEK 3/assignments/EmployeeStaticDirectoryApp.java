class CompanyEmployee {
    // Instance fields distinct per object
    private String empName;
    private double salary;

    // Static fields shared across all class instances
    public static String companyName = "Bright Horizon Technologies";
    public static int employeeCount = 0;

    public CompanyEmployee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++; // Increments once on every construction
    }

    // Static method without any instance-level references
    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class EmployeeStaticDirectoryApp {
    public static void main(String[] args) {
        // Instantiate three objects
        CompanyEmployee emp1 = new CompanyEmployee("Suresh", 55000.0);
        CompanyEmployee emp2 = new CompanyEmployee("Meena", 62000.0);
        CompanyEmployee emp3 = new CompanyEmployee("David", 48000.0);

        // Invoke static method exclusively through class name
        CompanyEmployee.printCompanyInfo();
    }
}