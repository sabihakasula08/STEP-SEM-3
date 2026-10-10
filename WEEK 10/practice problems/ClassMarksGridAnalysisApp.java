import java.util.ArrayList;
import java.util.List;

public class ClassMarksGridAnalysisApp {

    static class StudentScore {
        String name;
        int[] marks;
        int total;

        StudentScore(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
            this.total = marks[0] + marks[1] + marks[2];
        }
    }

    public static void main(String[] args) {
        List<StudentScore> students = new ArrayList<>();
        students.add(new StudentScore("Asha", new int[]{78, 85, 90}));
        students.add(new StudentScore("Ravi", new int[]{88, 92, 79}));
        students.add(new StudentScore("Neha", new int[]{65, 70, 95}));

        // 1. Totals
        StringBuilder totalsSb = new StringBuilder("Totals ");
        StudentScore topper = students.get(0);

        for (int i = 0; i < students.size(); i++) {
            StudentScore s = students.get(i);
            totalsSb.append(s.name).append(" ").append(s.total);
            if (i < students.size() - 1) totalsSb.append(", ");
            if (s.total > topper.total) {
                topper = s;
            }
        }
        System.out.println(totalsSb.toString());

        // 2. Subject Averages (3 subjects)[cite: 62]
        double[] subjectSums = new double[3];
        for (StudentScore s : students) {
            for (int j = 0; j < 3; j++) {
                subjectSums[j] += s.marks[j];
            }
        }
        int count = students.size();
        System.out.printf("averages %.2f, %.2f, %.2f\n",
                subjectSums[0] / count, subjectSums[1] / count, subjectSums[2] / count);

        // 3. Topper
        System.out.printf("topper %s (%d)\n", topper.name, topper.total);
    }
}