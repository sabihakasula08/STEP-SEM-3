enum PortalSubmissionStatus {
    SUBMITTED,
    GRADED
}

abstract class PortalAssignment {
    private final String title;
    private final int maxMarks;
    private final int dueDay;

    public PortalAssignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public int getDueDay() { return dueDay; }

    // Calculates final marks with late penalty applied[cite: 70, 71]
    public abstract int calculateFinalMarks(int awardedMarks, int lateDays);
    public abstract int getPenaltyPercentage(int lateDays);
}

class CodingAssignment extends PortalAssignment {
    public CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public int calculateFinalMarks(int awardedMarks, int lateDays) {
        if (lateDays <= 0) return awardedMarks;
        double penaltyFactor = 1.0 - (0.10 * lateDays);
        return (int) Math.round(awardedMarks * Math.max(0.0, penaltyFactor));
    }

    @Override
    public int getPenaltyPercentage(int lateDays) {
        return lateDays <= 0 ? 0 : lateDays * 10;
    }
}

class WrittenAssignment extends PortalAssignment {
    public WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public int calculateFinalMarks(int awardedMarks, int lateDays) {
        if (lateDays <= 0) return awardedMarks;
        double penaltyFactor = 1.0 - (0.20 * lateDays);
        return (int) Math.round(awardedMarks * Math.max(0.0, penaltyFactor));
    }

    @Override
    public int getPenaltyPercentage(int lateDays) {
        return lateDays <= 0 ? 0 : lateDays * 20;
    }
}

class PortalSubmission {
    private final String studentName;
    private final PortalAssignment assignment;
    private final int submitDay;
    private PortalSubmissionStatus status;

    public PortalSubmission(String studentName, PortalAssignment assignment, int submitDay) {
        this.studentName = studentName;
        this.assignment = assignment;
        this.submitDay = submitDay;
        this.status = PortalSubmissionStatus.SUBMITTED;

        int lateDays = Math.max(0, submitDay - assignment.getDueDay());
        if (lateDays == 0) {
            System.out.printf("%s's submission for '%s' received (on time). Status: %s.\n",
                    studentName, assignment.getTitle(), status);
        } else {
            System.out.printf("%s's submission for '%s' received (%d days late). Status: %s.\n",
                    studentName, assignment.getTitle(), lateDays, status);
        }
    }

    public void grade(int awardedMarks) {
        this.status = PortalSubmissionStatus.GRADED;
        int lateDays = Math.max(0, submitDay - assignment.getDueDay());
        int finalMarks = assignment.calculateFinalMarks(awardedMarks, lateDays);
        int penaltyPct = assignment.getPenaltyPercentage(lateDays);

        if (penaltyPct > 0) {
            System.out.printf("%s graded: %d/%d after %d%% late penalty. Status: %s.\n",
                    studentName, finalMarks, assignment.getMaxMarks(), penaltyPct, status);
        } else {
            System.out.printf("%s graded: %d/%d. Status: %s.\n",
                    studentName, finalMarks, assignment.getMaxMarks(), status);
        }
    }

    public boolean canResubmit() {
        return this.status != PortalSubmissionStatus.GRADED;
    }

    public String getTitle() { return assignment.getTitle(); }
}

public class AssignmentPortalApp {
    public static void submitWork(String studentName, PortalAssignment assignment, int day, PortalSubmission existing) {
        if (existing != null && !existing.canResubmit()) {
            System.out.printf("Cannot resubmit: '%s' has already been graded.\n", existing.getTitle());
            return;
        }
        new PortalSubmission(studentName, assignment, day);
    }

    public static void main(String[] args) {
        PortalAssignment linkedListLab = new CodingAssignment("Linked List Lab", 50, 10);
        PortalAssignment designEssay = new WrittenAssignment("Design Essay", 50, 12);

        // Asha submits on time[cite: 71]
        PortalSubmission ashaSub = new PortalSubmission("Asha", linkedListLab, 10);

        // Ravi submits 2 days late[cite: 71]
        PortalSubmission raviSub = new PortalSubmission("Ravi", designEssay, 14);

        // Faculty grading[cite: 71]
        ashaSub.grade(45);
        raviSub.grade(40);

        // Asha attempts resubmission post-grading[cite: 71]
        submitWork("Asha", linkedListLab, 15, ashaSub);
    }
}