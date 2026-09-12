class Employee {
    private String empId;
    private String empName;
    private double salary;
    private boolean isIntern;

    // Primary 3-parameter constructor for permanent employees
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = Math.max(0.0, salary);
        this.isIntern = false;
    }

    // Overloaded constructor for interns chaining to primary constructor via this(...)
    public Employee(String empId, String empName) {
        this(empId, empName, 0.0);
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.printf("%s | %s | Rs %.1f | Intern: %b\n", 
            this.empId, this.empName, this.salary, this.isIntern);
    }
}

public class EmployeeProfileApp {
    public static void main(String[] args) {
        // Permanent employee with designated salary
        Employee permanentEmp = new Employee("E-101", "Divya", 65000.0);

        // Intern chaining with 0.0 salary
        Employee internEmp = new Employee("E-102", "Arjun");

        permanentEmp.printProfile();
        internEmp.printProfile();
    }
}