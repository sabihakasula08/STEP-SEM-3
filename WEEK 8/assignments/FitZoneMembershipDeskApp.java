enum GymPlanStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

abstract class GymPlan {
    protected double baseMonthlyRate = 1000.00;

    public abstract String getPlanName();
    public abstract double calculateFee();
}

class MonthlyGymPlan extends GymPlan {
    @Override public String getPlanName() { return "Monthly"; }
    @Override public double calculateFee() {
        return baseMonthlyRate; // 1 month, full price[cite: 72]
    }
}

class QuarterlyGymPlan extends GymPlan {
    @Override public String getPlanName() { return "Quarterly"; }
    @Override public double calculateFee() {
        return (baseMonthlyRate * 3) * 0.90; // 3 months, 10% off[cite: 72]
    }
}

class AnnualGymPlan extends GymPlan {
    @Override public String getPlanName() { return "Annual"; }
    @Override public double calculateFee() {
        return (baseMonthlyRate * 12) * 0.75; // 12 months, 25% off[cite: 72]
    }
}

class FitZoneMembership {
    private final String memberName;
    private final GymPlan plan;
    private GymPlanStatus status;

    public FitZoneMembership(String memberName, GymPlan plan) {
        this.memberName = memberName;
        this.plan = plan;
        this.status = GymPlanStatus.ACTIVE;
        System.out.printf("%s membership created for %s. Fee: %,.2f. Status: %s.\n",
                plan.getPlanName(), memberName, plan.calculateFee(), status);
    }

    public void checkIn() {
        if (this.status == GymPlanStatus.ACTIVE) {
            System.out.printf("%s checked in successfully.\n", memberName);
        } else {
            System.out.printf("Check-in denied: %s's membership is %s.\n", memberName, status);
        }
    }

    public void freeze() {
        if (this.status == GymPlanStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return;
        }
        this.status = GymPlanStatus.FROZEN;
        System.out.printf("%s's membership frozen. Status: %s.\n", memberName, status);
    }

    public void unfreeze() {
        if (this.status == GymPlanStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return;
        }
        this.status = GymPlanStatus.ACTIVE;
        System.out.printf("%s's membership unfrozen. Status: %s.\n", memberName, status);
    }

    public void expire() {
        this.status = GymPlanStatus.EXPIRED;
        System.out.printf("%s's membership expired. Status: %s.\n", memberName, status);
    }
}

public class FitZoneMembershipDeskApp {
    public static void main(String[] args) {
        // Asha buys Quarterly, Ravi buys Monthly[cite: 72]
        FitZoneMembership asha = new FitZoneMembership("Asha", new QuarterlyGymPlan());
        FitZoneMembership ravi = new FitZoneMembership("Ravi", new MonthlyGymPlan());

        // Asha check-in, freeze, and attempted check-in[cite: 72]
        asha.checkIn();
        asha.freeze();
        asha.checkIn();

        // Ravi expires and attempts to freeze[cite: 72]
        ravi.expire();
        ravi.freeze();
    }
}