class ReportLibMember {
    protected String memberId;
    protected int booksBorrowed;

    public ReportLibMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.booksBorrowed = 0;
    }

    public String displayInfo() {
        return "General | Books: " + this.booksBorrowed;
    }
}

class ReportStudentMember extends ReportLibMember {
    private String course;

    public ReportStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return this.course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + this.course + " | Books: " + this.booksBorrowed;
    }
}

public class CirculationReportApp {

    // Builds batch report using StringBuilder and guarded downcasts[cite: 9]
    public static String batchPrint(ReportLibMember[] members) {
        StringBuilder report = new StringBuilder();

        for (ReportLibMember member : members) {
            report.append(member.displayInfo());

            // Safely downcast only after validating type with instanceof[cite: 9]
            if (member instanceof ReportStudentMember) {
                ReportStudentMember student = (ReportStudentMember) member;
                report.append(" [Course via downcast: ").append(student.getCourse()).append("]");
            }
            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {
        ReportLibMember[] roster = {
            new ReportLibMember("LB5", 3),
            new ReportStudentMember("STU6", 3, "ECE")
        };

        System.out.println(batchPrint(roster));
    }
}