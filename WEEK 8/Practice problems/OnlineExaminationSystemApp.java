import java.util.HashMap;
import java.util.Map;

abstract class ExamQuestion {
    private String prompt;
    private int points;

    public ExamQuestion(String prompt, int points) {
        this.prompt = prompt;
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends ExamQuestion {
    private String correctOption;

    public MultipleChoiceQuestion(String prompt, int points, String correctOption) {
        super(prompt, points);
        this.correctOption = correctOption;
    }

    @Override
    public boolean evaluate(String answer) {
        return this.correctOption.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends ExamQuestion {
    private boolean correctAnswer;

    public TrueFalseQuestion(String prompt, int points, boolean correctAnswer) {
        super(prompt, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return Boolean.toString(this.correctAnswer).equalsIgnoreCase(answer);
    }
}

class ExamAttempt {
    private String studentName;
    private String examName;
    private Map<Integer, String> recordedAnswers;
    private boolean isSubmitted;

    public ExamAttempt(String studentName, String examName) {
        this.studentName = studentName;
        this.examName = examName;
        this.recordedAnswers = new HashMap<>();
        this.isSubmitted = false;
        System.out.println(examName + " started by " + studentName + ".");
    }

    public void recordAnswer(int questionNumber, String answer) {
        if (isSubmitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        recordedAnswers.put(questionNumber, answer);
        System.out.println("Answer recorded for Question " + questionNumber + ".");
    }

    public void submit(Map<Integer, ExamQuestion> questions) {
        this.isSubmitted = true;
        System.out.println(examName + " submitted by " + studentName + ".");

        int totalScore = 0;
        int maxScore = 0;
        StringBuilder resultSummary = new StringBuilder("Result: ");

        for (Map.Entry<Integer, ExamQuestion> entry : questions.entrySet()) {
            int qNum = entry.getKey();
            ExamQuestion q = entry.getValue();
            maxScore += q.getPoints();

            String ans = recordedAnswers.get(qNum);
            boolean correct = (ans != null && q.evaluate(ans));

            if (correct) {
                totalScore += q.getPoints();
                resultSummary.append("Question ").append(qNum).append(": Correct (")
                        .append(q.getPoints()).append(" points), ");
            } else {
                resultSummary.append("Question ").append(qNum).append(": Incorrect (0 points), ");
            }
        }

        // Clean trailing comma
        if (resultSummary.toString().endsWith(", ")) {
            resultSummary.setLength(resultSummary.length() - 2);
        }

        System.out.println(resultSummary.toString() + ". Total score: " + totalScore + "/" + maxScore + ".");
    }
}

public class OnlineExaminationSystemApp {
    public static void main(String[] args) {
        Map<Integer, ExamQuestion> questions = new HashMap<>();
        questions.put(1, new MultipleChoiceQuestion("Which is correct?", 5, "C"));
        questions.put(2, new TrueFalseQuestion("Java is OOP?", 5, false)); // correct is false for demo score 5/10

        ExamAttempt attempt = new ExamAttempt("Student 1", "Exam A");
        attempt.recordAnswer(1, "C");
        attempt.recordAnswer(2, "True");

        attempt.submit(questions);

        // Attempting to change answer post-submission[cite: 68]
        attempt.recordAnswer(1, "B");
    }
}