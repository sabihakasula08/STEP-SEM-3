enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

class LeaveRequest {
    private String employeeName;
    private String dates;
    private LeaveStatus status;

    public LeaveRequest(String employeeName, String dates) {
        this.employeeName = employeeName;
        this.dates = dates;
        this.status = LeaveStatus.PENDING;
        System.out.printf("Leave request submitted for %s (%s). Status: %s.\n",
                employeeName, dates, status);
    }

    public LeaveStatus getStatus() {
        return this.status;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public String getDates() {
        return this.dates;
    }

    public void setStatus(LeaveStatus newStatus) {
        // Guard against reverting an Approved/Rejected request back to Pending[cite: 66, 67]
        if (this.status != LeaveStatus.PENDING && newStatus == LeaveStatus.PENDING) {
            System.out.printf("Cannot change leave request status from %s to Pending.\n", this.status);
            return;
        }
        this.status = newStatus;
    }
}

abstract class SystemEmployee {
    private String name;

    public SystemEmployee(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract boolean isEligibleForLeave(int days);
}

class FullTimeEmployee extends SystemEmployee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isEligibleForLeave(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends SystemEmployee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isEligibleForLeave(int days) {
        return days <= 10;
    }
}

class ManagerReviewer {
    private String managerName;

    public ManagerReviewer(String managerName) {
        this.managerName = managerName;
    }

    public void approve(LeaveRequest request) {
        request.setStatus(LeaveStatus.APPROVED);
        System.out.printf("%s's leave request (%s) approved. Status: %s.\n",
                request.getEmployeeName(), request.getDates(), request.getStatus());
    }

    public void reject(LeaveRequest request) {
        request.setStatus(LeaveStatus.REJECTED);
        System.out.printf("%s's leave request (%s) rejected. Status: %s.\n",
                request.getEmployeeName(), request.getDates(), request.getStatus());
    }
}

public class EmployeeLeaveWorkflowApp {
    public static void main(String[] args) {
        ManagerReviewer alice = new ManagerReviewer("Alice");
        ManagerReviewer bob = new ManagerReviewer("Bob");

        // John submits request, Alice approves[cite: 67]
        LeaveRequest johnReq = new LeaveRequest("John", "Jan 1-5");
        alice.approve(johnReq);

        // Jane submits request, Bob rejects[cite: 67]
        LeaveRequest janeReq = new LeaveRequest("Jane", "Feb 10-11");
        bob.reject(janeReq);

        // John attempts to revert status to Pending[cite: 67]
        johnReq.setStatus(LeaveStatus.PENDING);
    }
}