import java.util.Random;

public class BmiCalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi <= 24.9) {
            return "Normal";
        } else if (bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("=======================================================================");
        System.out.printf("%-10s | %-12s | %-12s | %-8s | %-12s\n", 
            "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("-----------------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);
            System.out.printf("Person %-3d | %-12.2f | %-12.2f | %-8.2f | %-12s\n",
                (i + 1), heights[i], weights[i], bmi, status);
        }
        System.out.println("=======================================================================");
    }

    public static void main(String[] args) {
        int teamSize = 10;
        double[] heights = new double[teamSize];
        double[] weights = new double[teamSize];
        Random random = new Random();

        for (int i = 0; i < teamSize; i++) {
            heights[i] = 1.50 + (random.nextDouble() * 0.45);
            weights[i] = 45.0 + (random.nextDouble() * 55.0);
        }

        printWellnessReport(heights, weights);
    }
}