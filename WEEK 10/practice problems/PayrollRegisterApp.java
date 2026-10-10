import java.util.ArrayList;
import java.util.List;

abstract class StaffMember {
    protected String name;

    public StaffMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();
    public abstract double calculatePay();

    @Override
    public String toString() {
        return String.format("Payslip[name=%s, type=%s, pay=%.0f]",
                name, getType(), calculatePay());
    }
}

class FullTimeStaff extends StaffMember {
    private double salary;

    public FullTimeStaff(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    @Override public String getType() { return "FullTime"; }
    @Override public double calculatePay() { return salary; }
}

class PartTimeStaff extends StaffMember {
    private double hours;
    private double rate;

    public PartTimeStaff(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override public String getType() { return "PartTime"; }
    @Override public double calculatePay() { return hours * rate; }
}

class InternStaff extends StaffMember {
    private double stipend;

    public InternStaff(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override public String getType() { return "Intern"; }
    @Override public double calculatePay() { return stipend; }
}

public class PayrollRegisterApp {
    public static void main(String[] args) {
        List<StaffMember> staffList = new ArrayList<>();
        staffList.add(new FullTimeStaff("Asha", 50000));
        staffList.add(new PartTimeStaff("Ravi", 80, 300));
        staffList.add(new InternStaff("Neha", 15000));

        double totalPay = 0;
        StaffMember topEarner = staffList.get(0);

        for (StaffMember member : staffList) {
            System.out.println(member);
            double pay = member.calculatePay();
            totalPay += pay;
            if (pay > topEarner.calculatePay()) {
                topEarner = member;
            }
        }

        System.out.printf("Total %.0f\n", totalPay);
        System.out.println("top earner " + topEarner.getName());
    }
}